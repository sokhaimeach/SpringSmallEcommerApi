package org.backend.smallecommerceapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "order_items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseId {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private String productName;

    @Positive
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Positive
    private int quantity = 1;

    @Positive
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal subTotal;

    public OrderItem(Order order, Product product, int quantity) {
        this.order = order;
        this.product = product;
        this.productName = product.getName();
        this.price = product.getPrice();
        this.quantity = quantity;
        calculateSubTotal();
    }

    public void calculateSubTotal() {
        subTotal = price.multiply(BigDecimal.valueOf(quantity));
    }
}
