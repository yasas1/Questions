package com.example.userroleauth.service.impl;

import com.example.userroleauth.dto.PermissionDto;
import com.example.userroleauth.dto.ResultResponse;
import com.example.userroleauth.model.Permission;
import com.example.userroleauth.repository.PermissionRepository;
import com.example.userroleauth.service.PermissionService;
import com.example.userroleauth.util.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    @Override
    public PermissionDto createPermission(PermissionDto permissionDto) {
        return ObjectMapper.mapPermissonEntityToDto(this.permissionRepository.save(ObjectMapper.mapPermissonDtoToEntity(permissionDto)));
    }

    @Override
    public PermissionDto updatePermission(long id, PermissionDto permissionDto) {
        Permission permission = this.permissionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        permission.setName(permissionDto.getName());
        return ObjectMapper.mapPermissonEntityToDto(this.permissionRepository.save(permission));
    }

    @Override
    public PermissionDto getPermissionById(long id) {
        return ObjectMapper.mapPermissonEntityToDto(this.permissionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found")));
    }

    @Override
    public ResultResponse<PermissionDto> getAllPermission(int pageNum, int pageSize, String sortBy, String sortDirection,String filterName) {

        Sort sort = Sort.unsorted();
        if (sortBy != null && sortDirection != null) {
            sort = sortDirection.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        }
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, sort);
        Page<Permission> pagedPermission;

        if (filterName == null || filterName.isBlank()) {
            pagedPermission = this.permissionRepository.findAll(pageRequest);
        } else {
            ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny()
                    .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
            pagedPermission = this.permissionRepository.findAll(Example.of(Permission.builder().name(filterName).build(), customExampleMatcher), pageRequest);
        }

        List<PermissionDto> content = pagedPermission.getContent().stream().map(ObjectMapper::mapPermissonEntityToDto).collect(Collectors.toList());

        ResultResponse<PermissionDto> resultResponse = new ResultResponse<>();
        resultResponse.setContent(content);
        resultResponse.setPageNo(pagedPermission.getNumber());
        resultResponse.setPageSize(pagedPermission.getSize());
        resultResponse.setTotalElement(pagedPermission.getTotalElements());
        resultResponse.setTotalPages(pagedPermission.getTotalPages());
        return resultResponse;
    }
}
