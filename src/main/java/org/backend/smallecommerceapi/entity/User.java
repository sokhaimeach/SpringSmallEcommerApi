package org.backend.smallecommerceapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.backend.smallecommerceapi.enums.Role;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
}
