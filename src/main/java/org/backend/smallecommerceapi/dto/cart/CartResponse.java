package org.backend.smallecommerceapi.dto.cart;

import lombok.Getter;
import lombok.Setter;
import org.backend.smallecommerceapi.dto.user.UserResponse;

import java.util.List;

@Getter
@Setter
public class CartResponse {
    private Long id;
    private UserResponse user;
    private List<CartItemResponse> items;
}
