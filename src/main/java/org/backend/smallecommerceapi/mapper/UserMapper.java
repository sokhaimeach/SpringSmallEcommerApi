package org.backend.smallecommerceapi.mapper;

import org.backend.smallecommerceapi.dto.auth.RegisterRequest;
import org.backend.smallecommerceapi.dto.user.UpdateUserRequest;
import org.backend.smallecommerceapi.dto.user.UserResponse;
import org.backend.smallecommerceapi.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponse(User user);
    User fromRegisterToEntity(RegisterRequest registerRequest);
    User fromUpdateToEntity(UpdateUserRequest updateUserRequest);
}
