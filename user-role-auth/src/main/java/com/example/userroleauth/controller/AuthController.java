package com.example.userroleauth.controller;

import com.example.userroleauth.auth.JwtUtil;
import com.example.userroleauth.dto.LoginRequest;
import com.example.userroleauth.dto.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping(path = "user-role-auth-service/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenProvider;

    @PostMapping(path = "/authentication")
    public ResponseEntity<LoginResponse> createUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authenticate = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        String token = jwtTokenProvider.createToken(authenticate.getName(), getAuthorities(authenticate.getAuthorities()));
        return new ResponseEntity<>(new LoginResponse(token), HttpStatus.OK);

    }

    private String getAuthorities(Collection<? extends GrantedAuthority> authorities) {
        HashSet<String> authoritiesSet = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toCollection(HashSet::new));
        return String.join(",", authoritiesSet);
    }
}
