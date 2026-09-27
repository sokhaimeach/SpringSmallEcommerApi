package org.backend.smallecommerceapi.dto.auth;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.backend.smallecommerceapi.enums.Role;

@Getter
@Setter
public class AuthResponse {
    private Long id;
    private String name;
    private Role role;
    private String accessToken;
}
