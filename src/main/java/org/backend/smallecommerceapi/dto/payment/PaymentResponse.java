package org.backend.smallecommerceapi.dto.payment;

import lombok.Getter;
import lombok.Setter;
import org.backend.smallecommerceapi.enums.PaymentMethod;
import org.backend.smallecommerceapi.enums.PaymentStatus;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentResponse {
    private Long id;
    private BigDecimal amount;
    private PaymentMethod method;
    private PaymentStatus status;
    private String transactionId;
}
