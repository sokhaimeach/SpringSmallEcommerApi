package org.backend.smallecommerceapi.dto.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.backend.smallecommerceapi.enums.Role;

@Setter
@Getter
public class UpdateUserRequest {
    @NotEmpty
    @Size(min = 3)
    private String name;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private Role role;
}
