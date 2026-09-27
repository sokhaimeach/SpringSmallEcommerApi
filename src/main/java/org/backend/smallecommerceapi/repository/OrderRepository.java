package org.backend.smallecommerceapi.repository;

import org.backend.smallecommerceapi.entity.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = "user")
    List<Order> findAllByOrderByCreatedAtDesc();

    /**
     * @param id must not be {@literal null}.
     * @return
     */
    @EntityGraph(
            attributePaths = {
                    "user",
                    "orderItems",
                    "payments"
            }
    )
    @Override
    Optional<Order> findById(Long id);
}
