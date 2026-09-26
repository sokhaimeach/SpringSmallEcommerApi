package org.backend.smallecommerceapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cart_items")
public class CartItem extends BaseId {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id",  nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Positive
    private int quantity = 1;
}
