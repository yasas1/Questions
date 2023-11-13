package com.example.userroleauth.service.impl;

import com.example.userroleauth.dto.ResultResponse;
import com.example.userroleauth.dto.RoleDto;
import com.example.userroleauth.model.Role;
import com.example.userroleauth.model.User;
import com.example.userroleauth.repository.PermissionRepository;
import com.example.userroleauth.repository.RoleRepository;
import com.example.userroleauth.repository.UserRepository;
import com.example.userroleauth.service.RoleService;
import com.example.userroleauth.util.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        Role role = ObjectMapper.mapRoleDtoToCreateEntity(roleDto);
        if (roleDto.getPermissionIds() != null && !roleDto.getPermissionIds().isEmpty()) {
            role.setPermissions(roleDto.getPermissionIds().stream().map(permissionId -> this.permissionRepository.findById(permissionId)
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Permission is not found")))
                    .collect(Collectors.toSet()));
        }
        role.setCreatedByUser(this.userRepository.findById(roleDto.getCreatedByUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found")));
        Role savedRole = this.roleRepository.save(role);
        RoleDto savedRoleDto = ObjectMapper.mapRoleCreatedEntityToDto(savedRole);
        savedRoleDto.setPermissions(savedRole.getPermissions().stream().map(ObjectMapper::mapPermissonEntityToDto).collect(Collectors.toSet()));
        return savedRoleDto;
    }

    @Override
    public RoleDto updateRole(long id, RoleDto roleDto) {
        Role role = this.roleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role is not found"));
        if (roleDto.getPermissionIds() == null || roleDto.getPermissionIds().isEmpty()) {
            role.setPermissions(Collections.emptySet());
        } else {
            role.setPermissions(roleDto.getPermissionIds().stream().map(permissionId -> this.permissionRepository.findById(permissionId)
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Permission is not found")))
                    .collect(Collectors.toSet()));
        }
        role.setName(roleDto.getName());
        Role updatedRole = this.roleRepository.save(role);
        RoleDto updatedRoleDto = ObjectMapper.mapRoleCreatedEntityToDto(updatedRole);
        updatedRoleDto.setPermissions(updatedRole.getPermissions().stream().map(ObjectMapper::mapPermissonEntityToDto).collect(Collectors.toSet()));
        return updatedRoleDto;
    }

    @Override
    public RoleDto getRoleById(long id) {
        Role role = this.roleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role is not found"));
        RoleDto roleDto = ObjectMapper.mapRoleCreatedEntityToDto(role);
        roleDto.setPermissions(role.getPermissions().stream().map(ObjectMapper::mapPermissonEntityToDto).collect(Collectors.toSet()));
        roleDto.setCreatedByUser(ObjectMapper.mapUserEntityToDto(role.getCreatedByUser()));
        return roleDto;
    }

    @Override
    public ResultResponse<RoleDto> listAllRoles() {
        PageRequest pageRequest = PageRequest.of(0, 100);
        Page<Role> roles = this.roleRepository.findAll(pageRequest);

        ResultResponse<RoleDto> resultResponse = new ResultResponse<>();
        resultResponse.setContent(roles.getContent().stream().map(ObjectMapper::mapRoleCreatedEntityToDto).collect(Collectors.toList()));
        resultResponse.setPageNo(roles.getNumber());
        resultResponse.setPageSize(roles.getSize());
        resultResponse.setTotalElement(roles.getTotalElements());
        resultResponse.setTotalPages(roles.getTotalPages());
        return resultResponse;
    }
}
