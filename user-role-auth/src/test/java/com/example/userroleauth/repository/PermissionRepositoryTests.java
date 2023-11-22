package com.example.userroleauth.repository;

import com.example.userroleauth.model.Permission;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PermissionRepositoryTests {

    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    public void savePermissionTest() {
        // BDD : Arrange / Act / Assert

        // Arrange
        Permission permission = Permission.builder()
                .name("User-FullAccess")
                .build();

        // Act
        Permission savedPermission = permissionRepository.save(permission);

        // Asset
        Assertions.assertThat(savedPermission).isNotNull();
        Assertions.assertThat(savedPermission.getId()).isPositive();
        Assertions.assertThat(savedPermission.getName()).isEqualTo(permission.getName());


    }

    @Test
    public void findPermissionByIdTest() {
        // BDD : Arrange / Act / Assert

        // Arrange
        Permission permission = Permission.builder()
                .name("User-FullAccess")
                .build();


        Permission savedPermission = permissionRepository.save(permission);

        // Act
        Optional<Permission> permissionId = permissionRepository.findById(savedPermission.getId());

        // Asset
        Assertions.assertThat(permissionId).isPresent();
        Assertions.assertThat(permissionId.get().getId()).isPositive();
        Assertions.assertThat(permissionId.get().getName()).isEqualTo(savedPermission.getName());


    }
}
