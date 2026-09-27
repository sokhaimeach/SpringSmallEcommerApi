package org.backend.smallecommerceapi.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemResponse {
    private Long id;
    private String productName;
    private BigDecimal price;
    private int quantity;
    private BigDecimal subTotal;
}
