package com.example.userroleauth.service;

import com.example.userroleauth.dto.PermissionDto;
import com.example.userroleauth.dto.ResultResponse;
import com.example.userroleauth.model.Permission;
import com.example.userroleauth.repository.PermissionRepository;
import com.example.userroleauth.service.impl.PermissionServiceImpl;
import com.example.userroleauth.util.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PermissionServiceTest {

    @Mock
    private PermissionRepository permissionRepository;

    @InjectMocks
    private PermissionServiceImpl permissionService;

    Permission userFUllAccessPermission;
    Permission userReadOnlyPermission;

    @BeforeEach
    public void init() {
        userFUllAccessPermission = Permission.builder().id(1L).name("User-FullAccess").build();
        userReadOnlyPermission = Permission.builder().id(2L).name("User-ReadOnly").build();
    }

    @Test
    void createPermissionTest() {
        // BDD : Arrange / Act / Assert

        // Arrange

        PermissionDto permissionDto = PermissionDto.builder()
                .name(userFUllAccessPermission.getName())
                .build();

        Mockito.when(permissionRepository.save(Mockito.any(Permission.class))).thenReturn(userFUllAccessPermission);

        // Act
        PermissionDto createdPermissionDto = permissionService.createPermission(permissionDto);

        // Assert
        Assertions.assertThat(createdPermissionDto).isNotNull();
        Assertions.assertThat(createdPermissionDto.getId()).isPositive();
        Assertions.assertThat(createdPermissionDto.getName()).isEqualTo(permissionDto.getName());

    }

    @Test
    void getAllPermissionTestPathOne() {


        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("name").ascending());
        List<Permission> permissionList = Arrays.asList(userFUllAccessPermission, userReadOnlyPermission);

        PageImpl<Permission> pagedPermissions = new PageImpl<Permission>(permissionList, pageRequest, 2);

        Mockito.when(permissionRepository.findAll(Mockito.any(Pageable.class))).thenReturn(pagedPermissions);

        ResultResponse<PermissionDto> allPermission = permissionService.getAllPermission(0, 10, "name", "ASC", null);

        Assertions.assertThat(allPermission).isNotNull();
        Assertions.assertThat(allPermission.getPageNo()).isZero();
        Assertions.assertThat(allPermission.getPageSize()).isEqualTo(10);
        Assertions.assertThat(allPermission.getTotalElement()).isEqualTo(2);
        Assertions.assertThat(allPermission.getTotalPages()).isEqualTo(1);
        Assertions.assertThat(allPermission.getContent()).hasSize(2);
    }


    @Test
    void getAllPermissionTestPathTwo() {
        Page<Permission> pagedPermissions = Mockito.mock(Page.class);

        Mockito.when(permissionRepository.findAll(Mockito.any(Example.class), Mockito.any(Pageable.class))).thenReturn(pagedPermissions);

        ResultResponse<PermissionDto> allPermission = permissionService.getAllPermission(0, 10, "name", "ASC", "user");

        Assertions.assertThat(allPermission).isNotNull();
    }

}
