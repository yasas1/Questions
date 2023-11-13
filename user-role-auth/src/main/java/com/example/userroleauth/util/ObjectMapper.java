package com.example.userroleauth.util;

import com.example.userroleauth.dto.PermissionDto;
import com.example.userroleauth.dto.RoleDto;
import com.example.userroleauth.dto.UserDto;
import com.example.userroleauth.model.Permission;
import com.example.userroleauth.model.Role;
import com.example.userroleauth.model.User;

import java.util.Set;
import java.util.stream.Collectors;

public class ObjectMapper {

    private ObjectMapper() {
    }

    public static Permission mapPermissonDtoToEntity(PermissionDto permissionDto) {
        return Permission.builder()
                .name(permissionDto.getName())
                .build();
    }

    public static PermissionDto mapPermissonEntityToDto(Permission permission) {
        return PermissionDto.builder()
                .id(permission.getId())
                .name(permission.getName())
                .build();
    }

    public static User mapUserDtoToCreateEntity(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .build();
    }

    public static UserDto mapUserEntityToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }


    public static UserDto mapUserEntityWithRoleDetailToDto(User user) {
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
        if (user.getUserRoleReferences() != null && !user.getUserRoleReferences().isEmpty()) {
            Set<RoleDto> roles = user.getUserRoleReferences().stream()
                    .map(r -> {
                        RoleDto roleDto = mapRoleCreatedEntityToDto(r.getRole());
                        roleDto.setPermissions(r.getRole().getPermissions().stream().map(ObjectMapper::mapPermissonEntityToDto).collect(Collectors.toSet()));
                        return roleDto;
                    }).collect(Collectors.toSet());
            userDto.setRoles(roles);
        }
        return userDto;
    }

    public static Role mapRoleDtoToCreateEntity(RoleDto roleDto) {
        return Role.builder()
                .name(roleDto.getName())
                .createdDateTime(System.currentTimeMillis())
                .build();
    }

    public static RoleDto mapRoleCreatedEntityToDto(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .createdByUserId(role.getCreatedByUser().getId())
                .createdDateTime(role.getCreatedDateTime())
                .build();
    }
}
