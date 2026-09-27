package org.backend.smallecommerceapi.dto.user;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.backend.smallecommerceapi.enums.Role;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Role role;
}
