package com.example.userroleauth.service;

import com.example.userroleauth.dto.ResultResponse;
import com.example.userroleauth.dto.UserDto;
import com.example.userroleauth.dto.UserRoleAssignRequest;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(long id, UserDto userDto);
    void assignUserRoles(long id, UserRoleAssignRequest userRoleAssignRequest);
    UserDto getUserById(long id);
   ResultResponse<UserDto> listAllUsers();
}
