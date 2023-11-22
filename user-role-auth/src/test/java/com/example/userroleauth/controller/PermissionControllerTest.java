package com.example.userroleauth.controller;

import com.example.userroleauth.dto.PermissionDto;
import com.example.userroleauth.model.Permission;
import com.example.userroleauth.service.PermissionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


//@WebMvcTest(PermissionController.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PermissionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PermissionService permissionService;

    private ObjectMapper objectMapper = new ObjectMapper();

    Permission userFUllAccessPermission;
    Permission userReadOnlyPermission;

    PermissionDto userFUllAccessPermissionDtoRequest;
    PermissionDto userFUllAccessPermissionDtoResponse;


    @BeforeEach
    public void init() {
        userFUllAccessPermission = Permission.builder().id(1L).name("User-FullAccess").build();
        userReadOnlyPermission = Permission.builder().id(2L).name("User-ReadOnly").build();
        userFUllAccessPermissionDtoRequest = PermissionDto.builder().name(userFUllAccessPermission.getName()).build();
        userFUllAccessPermissionDtoResponse = PermissionDto.builder().id(userFUllAccessPermission.getId()).name(userFUllAccessPermission.getName()).build();
    }

    @Test
    void createPermissionTest() throws Exception {
        BDDMockito.given(permissionService.createPermission(ArgumentMatchers.any())).willReturn(userFUllAccessPermissionDtoResponse);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/user-role-auth-service/permissions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userFUllAccessPermissionDtoRequest))
        );

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
