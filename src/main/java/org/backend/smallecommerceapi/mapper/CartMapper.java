package org.backend.smallecommerceapi.mapper;

import org.backend.smallecommerceapi.dto.cart.CartResponse;
import org.backend.smallecommerceapi.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {
                CartItemMapper.class,
                UserMapper.class
        }
)
public interface CartMapper {
    @Mapping(target = "items", source = "cartItems")
    CartResponse toResponse(Cart cart);
}
