package com.example.userroleauth.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "user_role_reference")
public class UserRoleReference {
    @EmbeddedId
    private UserRoleKey id;
    @Column(name = "createdDateTime")
    private long createdDateTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "roleId", referencedColumnName = "id")
    @MapsId("roleId")
    private Role role;

    public UserRoleReference(long createdDateTime, User user, Role role) {
        this.id = new UserRoleKey(user.getId(), role.getId());
        this.createdDateTime = createdDateTime;
        this.user = user;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleReference that = (UserRoleReference) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
