package org.backend.smallecommerceapi.repository;

import org.backend.smallecommerceapi.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
