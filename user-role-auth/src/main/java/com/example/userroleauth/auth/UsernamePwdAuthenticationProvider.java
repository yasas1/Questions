package com.example.userroleauth.auth;

import com.example.userroleauth.model.Permission;
import com.example.userroleauth.model.User;
import com.example.userroleauth.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UsernamePwdAuthenticationProvider implements AuthenticationProvider {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = this.userRepository.findUserByEmail(username).orElseThrow(() -> new BadCredentialsException("No user registered with this credentials"));
        if (this.passwordEncoder.matches(password, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(username, password, getGrantedAuthorities(user));
        } else {
            throw new BadCredentialsException("Invalid password");
        }
    }

    private static List<GrantedAuthority> getGrantedAuthorities(User user) {
        Set<String> permissions = user.getUserRoleReferences().stream()
                .flatMap(r -> r.getRole().getPermissions().stream().map(Permission::getName)).collect(Collectors.toSet());
        List<GrantedAuthority> authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
