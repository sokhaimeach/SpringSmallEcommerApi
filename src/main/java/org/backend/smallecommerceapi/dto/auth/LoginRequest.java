package org.backend.smallecommerceapi.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
