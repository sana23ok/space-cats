package labs.spring.spacecatsecommerce.service;

import labs.spring.spacecatsecommerce.domain.payment.Payment;
import labs.spring.spacecatsecommerce.domain.payment.PaymentTransaction;

public interface PaymentService {

    PaymentTransaction processPayment(Payment payment);

}