package com.example.userroleauth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RoleDto {
    private Long id;
    @NotNull(message = "Name is mandatory")
    @NotEmpty(message = "Name is mandatory")
    @Size(min = 2, message = "Role name should have at least 2 characters")
    private String name;
    private long createdDateTime;
    @NotNull(message = "User id is mandatory")
    private Long createdByUserId;
    private UserDto createdByUser;
    private List<Long> permissionIds;
    private Set<PermissionDto> permissions;

}
