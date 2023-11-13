package com.example.userroleauth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserRoleAssignRequest {
    @NotEmpty(message = "Roles are mandatory")
    private List<Long> roleIds;
}
