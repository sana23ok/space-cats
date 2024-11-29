package labs.spring.spacecatsecommerce.service.impl;

import labs.spring.spacecatsecommerce.domain.order.Order;
import labs.spring.spacecatsecommerce.domain.order.OrderContext;
import labs.spring.spacecatsecommerce.domain.order.OrderEntry;
import labs.spring.spacecatsecommerce.domain.payment.PaymentTransaction;
import labs.spring.spacecatsecommerce.repository.CustomerRepository;
import labs.spring.spacecatsecommerce.repository.entity.CustomerEntity;
import labs.spring.spacecatsecommerce.service.OrderService;
import labs.spring.spacecatsecommerce.service.PaymentService;
import labs.spring.spacecatsecommerce.service.exception.PaymentTransactionFailed;
import labs.spring.spacecatsecommerce.service.mapper.PaymentMapper;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static labs.spring.spacecatsecommerce.common.PaymentStatus.FAILURE;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;
    private final CustomerRepository customerRepository;

    @Transactional
    @Override
    public Order placeOrder(OrderContext orderContext) {
        log.info("Placing order for cart with id: {} and customerId {}", orderContext.getCartId(), orderContext.getCustomerReference());

        Optional<CustomerEntity> customerEntityOpt = customerRepository.findById(orderContext.getCustomerReference());

        if (customerEntityOpt.isEmpty()) {
            log.error("Customer with id {} not found", orderContext.getCustomerReference());
            throw new RuntimeException("Customer not found");
        }

        PaymentTransaction paymentTransaction = paymentService.processPayment(paymentMapper.toPayment(orderContext));
        if (FAILURE.equals(paymentTransaction.getStatus())) {
            throw new PaymentTransactionFailed(paymentTransaction.getId(), orderContext.getCartId());
        }

        return createOrderMock(orderContext.getCartId(),
                orderContext.getEntries(),
                orderContext.getTotalPrice(),
                orderContext.getCustomerReference(),
                paymentTransaction.getId());
    }

    private Order createOrderMock(String cartId, List<OrderEntry> entries, Double totalPrice, Long customerId, UUID transactionId) {
        return Order.builder()
                .id(String.valueOf(ThreadLocalRandom.current().nextLong()))
                .transactionId(transactionId)
                .cartId(cartId)
                .entries(entries)
                .totalPrice(totalPrice)
                .consumerReference(customerId)
                .build();
    }

}
