package org.backend.smallecommerceapi.repository;

import org.backend.smallecommerceapi.entity.Cart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @EntityGraph(attributePaths = {"user", "cartItems", "cartItems.product"})
    Optional<Cart> findByUserId(Long userId);
}
