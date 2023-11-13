package com.example.userroleauth.service.impl;

import com.example.userroleauth.dto.ResultResponse;
import com.example.userroleauth.dto.UserDto;
import com.example.userroleauth.dto.UserRoleAssignRequest;
import com.example.userroleauth.model.Permission;
import com.example.userroleauth.model.Role;
import com.example.userroleauth.model.User;
import com.example.userroleauth.model.UserRoleReference;
import com.example.userroleauth.repository.RoleRepository;
import com.example.userroleauth.repository.UserRepository;
import com.example.userroleauth.service.UserService;
import com.example.userroleauth.util.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = this.userRepository.findUserByEmail(usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found for the email: " + usernameOrEmail));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), this.convertRolesToAuthorities(user));
    }

    private Collection< ? extends GrantedAuthority> convertRolesToAuthorities(User user){
        Set<String> permissions = user.getUserRoleReferences().stream()
                .flatMap(r -> r.getRole().getPermissions().stream().map(Permission::getName)).collect(Collectors.toSet());
        return permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = ObjectMapper.mapUserDtoToCreateEntity(userDto);
        user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
        return ObjectMapper.mapUserEntityToDto(this.userRepository.save(user));
    }

    @Override
    public UserDto updateUser(long id, UserDto userDto) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        return ObjectMapper.mapUserEntityToDto(this.userRepository.save(user));
    }

    @Override
    public void assignUserRoles(long id, UserRoleAssignRequest userRoleAssignRequest) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found"));
        if (userRoleAssignRequest.getRoleIds() == null || userRoleAssignRequest.getRoleIds().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Roles are mandatory");
        }
        long currentTimeMillis = System.currentTimeMillis();
        Set<UserRoleReference> userRoleReferences = userRoleAssignRequest.getRoleIds().stream()
                .map(roleId -> {
                    Role role = this.roleRepository.findById(roleId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role is not found"));
                    return new UserRoleReference(currentTimeMillis, user, role);
                }).collect(Collectors.toSet());
        user.setUserRoleReferences(userRoleReferences);
        this.userRepository.save(user);
    }

    @Override
    public UserDto getUserById(long id) {
        return ObjectMapper.mapUserEntityWithRoleDetailToDto(this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found")));
    }

    @Override
    public ResultResponse<UserDto> listAllUsers() {
        PageRequest pageRequest = PageRequest.of(0, 100);
        Page<User> users = this.userRepository.findAll(pageRequest);

        ResultResponse<UserDto> resultResponse = new ResultResponse<>();
        resultResponse.setContent(users.getContent().stream().map(ObjectMapper::mapUserEntityToDto).collect(Collectors.toList()));
        resultResponse.setPageNo(users.getNumber());
        resultResponse.setPageSize(users.getSize());
        resultResponse.setTotalElement(users.getTotalElements());
        resultResponse.setTotalPages(users.getTotalPages());
        return resultResponse;
    }

}
