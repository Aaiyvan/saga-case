package dev.aaiyvan.paymentservice.repository;

import dev.aaiyvan.paymentservice.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
