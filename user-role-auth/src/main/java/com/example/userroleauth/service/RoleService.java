package com.example.userroleauth.service;

import com.example.userroleauth.dto.ResultResponse;
import com.example.userroleauth.dto.RoleDto;

public interface RoleService {
    RoleDto createRole(RoleDto roleDto);
    RoleDto updateRole(long id, RoleDto roleDto);
    RoleDto getRoleById(long id);
    ResultResponse<RoleDto> listAllRoles();
}
