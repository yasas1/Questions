package com.example.userroleauth.controller;

import com.example.userroleauth.dto.ResultResponse;
import com.example.userroleauth.dto.RoleDto;
import com.example.userroleauth.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(path = "user-role-auth-service/roles")
public class RoleController {
    private final RoleService roleService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleDto> createUser(@Valid @RequestBody RoleDto roleDto) {
        return new ResponseEntity<>(this.roleService.createRole(roleDto), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleDto> updateUser(@PathVariable long id, @Valid @RequestBody RoleDto roleDto) {
        return new ResponseEntity<>(this.roleService.updateRole(id, roleDto), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleDto> getUserById(@PathVariable long id) {
        return new ResponseEntity<>(this.roleService.getRoleById(id), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultResponse<RoleDto>> listAllUsers() {
        return new ResponseEntity<>(this.roleService.listAllRoles(), HttpStatus.OK);
    }
}
