package com.example.userroleauth.controller;

import com.example.userroleauth.dto.ResultResponse;
import com.example.userroleauth.dto.UserDto;
import com.example.userroleauth.dto.UserRoleAssignRequest;
import com.example.userroleauth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(path = "user-role-auth-service/users")
public class UserController {
    private final UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(this.userService.createUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateUser(@PathVariable long id, @Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(this.userService.updateUser(id, userDto), HttpStatus.OK);
    }

    @PostMapping(path = "/{id}/roles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@PathVariable long id, @Valid @RequestBody UserRoleAssignRequest userRoleAssignRequest) {
        this.userService.assignUserRoles(id, userRoleAssignRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUserById(@PathVariable long id) {
        return new ResponseEntity<>(this.userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultResponse<UserDto>> listAllUsers() {
        return new ResponseEntity<>(this.userService.listAllUsers(), HttpStatus.OK);
    }
}
