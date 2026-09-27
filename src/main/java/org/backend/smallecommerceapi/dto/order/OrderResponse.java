package org.backend.smallecommerceapi.dto.order;

import lombok.Getter;
import lombok.Setter;
import org.backend.smallecommerceapi.dto.user.UserResponse;
import org.backend.smallecommerceapi.enums.OrderStatus;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderResponse {
    private Long id;
    private UserResponse user;
    private BigDecimal totalAmount;
    private OrderStatus status;
}
