package org.backend.smallecommerceapi.service;

import org.backend.smallecommerceapi.repository.CartItemRepository;
import org.backend.smallecommerceapi.repository.CartRepository;
import org.backend.smallecommerceapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    public CartService(
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            UserRepository userRepository
    ) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
    }
}
