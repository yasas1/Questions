package com.example.userroleauth.controller;

import com.example.userroleauth.dto.PermissionDto;
import com.example.userroleauth.dto.ResultResponse;
import com.example.userroleauth.service.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(path = "user-role-auth-service/permissions")
public class PermissionController {
    private final PermissionService permissionService;
    private final PasswordEncoder passwordEncoder;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PermissionDto> createPermission(@Valid @RequestBody PermissionDto permissionDto) {
        return new ResponseEntity<>(this.permissionService.createPermission(permissionDto), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PermissionDto> updatePermission(@PathVariable long id, @Valid @RequestBody PermissionDto permissionDto) {
        return new ResponseEntity<>(this.permissionService.updatePermission(id, permissionDto), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PermissionDto> getPermission(@PathVariable long id) {
        return new ResponseEntity<>(this.permissionService.getPermissionById(id), HttpStatus.OK);
    }

    @PreAuthorize("authentication.name == 'admin@example.com' and #passwordEncoder.matches('pwd1234', authentication.credentials)")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultResponse<PermissionDto>> listAllPermissions(@RequestParam(required = false, defaultValue = "0") int pageNum,
                                                                          @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                                          @RequestParam(required = false) String sortBy,
                                                                          @RequestParam(required = false) String sortDirection,
                                                                          @RequestParam(required = false) String filterName) {
        return new ResponseEntity<>(this.permissionService.getAllPermission(pageNum, pageSize, sortBy, sortDirection, filterName), HttpStatus.OK);
    }
}
