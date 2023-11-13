package com.example.userroleauth.service;

import com.example.userroleauth.dto.PermissionDto;
import com.example.userroleauth.dto.ResultResponse;

public interface PermissionService {

    PermissionDto createPermission(PermissionDto permissionDto);

    PermissionDto updatePermission(long id, PermissionDto permissionDto);
    PermissionDto getPermissionById(long id);
    ResultResponse<PermissionDto> getAllPermission(int pageNum, int pageSize, String sortBy, String sortDirection, String filterName);
}
