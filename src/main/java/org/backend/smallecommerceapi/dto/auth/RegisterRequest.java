package org.backend.smallecommerceapi.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.backend.smallecommerceapi.dto.user.UpdateUserRequest;
import org.backend.smallecommerceapi.enums.Role;

@Setter
@Getter
public class RegisterRequest extends UpdateUserRequest {
    @NotEmpty
    private String password;
}
