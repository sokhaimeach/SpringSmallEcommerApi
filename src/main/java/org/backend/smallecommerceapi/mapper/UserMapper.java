package org.backend.smallecommerceapi.mapper;

import org.backend.smallecommerceapi.dto.auth.AuthResponse;
import org.backend.smallecommerceapi.dto.auth.RegisterRequest;
import org.backend.smallecommerceapi.dto.user.UpdateUserRequest;
import org.backend.smallecommerceapi.dto.user.UserResponse;
import org.backend.smallecommerceapi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponse(User user);
    User fromRegisterToEntity(RegisterRequest registerRequest);
    User fromUpdateToEntity(UpdateUserRequest updateUserRequest);

    @Mapping(target = "accessToken", source = "accessToken")
    AuthResponse toAuthResponse(User user, String accessToken);
}
