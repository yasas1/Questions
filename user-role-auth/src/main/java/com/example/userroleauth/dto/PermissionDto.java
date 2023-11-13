package com.example.userroleauth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PermissionDto {
    private Long id;
    @NotEmpty
    @Size(min = 2, message = "Permission name should have at least 2 characters")
    private String name;
}
