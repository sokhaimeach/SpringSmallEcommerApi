package org.backend.smallecommerceapi.service;

import org.backend.smallecommerceapi.repository.CartRepository;
import org.backend.smallecommerceapi.repository.OrderRepository;
import org.backend.smallecommerceapi.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final PaymentRepository paymentRepository;

    public OrderService(
            OrderRepository orderRepository,
            CartRepository cartRepository,
            PaymentRepository paymentRepository
    ) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.paymentRepository = paymentRepository;
    }
}
