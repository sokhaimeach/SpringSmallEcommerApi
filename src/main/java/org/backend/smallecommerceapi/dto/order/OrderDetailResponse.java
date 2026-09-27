package org.backend.smallecommerceapi.dto.order;

import lombok.Getter;
import lombok.Setter;
import org.backend.smallecommerceapi.dto.payment.PaymentResponse;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderDetailResponse extends OrderResponse {
    private List<OrderItemResponse> items = new ArrayList<>();
    private List<PaymentResponse> payments = new ArrayList<>();
}
