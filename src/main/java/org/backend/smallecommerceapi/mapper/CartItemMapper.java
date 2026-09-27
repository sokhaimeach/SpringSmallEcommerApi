package org.backend.smallecommerceapi.mapper;

import org.backend.smallecommerceapi.dto.cart.CartItemResponse;
import org.backend.smallecommerceapi.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "productImageUrl", source = "product.imageUrl")
    @Mapping(target = "productPrice", source = "product.price")
    CartItemResponse toResponse(CartItem cartItem);
}
