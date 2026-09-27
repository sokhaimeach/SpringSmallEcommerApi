package org.backend.smallecommerceapi.repository;

import org.backend.smallecommerceapi.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
