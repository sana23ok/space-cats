//package labs.spring.spacecatsecommerce.service.impl;
//
//import labs.spring.spacecatsecommerce.domain.order.Order;
//import labs.spring.spacecatsecommerce.domain.order.OrderContext;
//import labs.spring.spacecatsecommerce.domain.order.OrderEntry;
//import labs.spring.spacecatsecommerce.domain.payment.PaymentTransaction;
//import labs.spring.spacecatsecommerce.repository.CustomerRepository;
//import labs.spring.spacecatsecommerce.repository.entity.CustomerEntity;
//import labs.spring.spacecatsecommerce.service.exception.PaymentTransactionFailed;
//import labs.spring.spacecatsecommerce.service.mapper.PaymentMapper;
//import labs.spring.spacecatsecommerce.service.PaymentService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class OrderServiceImplTest {
//
//    @Mock
//    private PaymentService paymentService;
//
//    @Mock
//    private PaymentMapper paymentMapper;
//
//    @Mock
//    private CustomerRepository customerRepository;
//
//    @InjectMocks
//    private OrderServiceImpl orderService;
//
//    private OrderContext orderContext;
//    private CustomerEntity customerEntity;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        // Mock order context data
//        orderContext = new OrderContext("cart123", 1L, 100.0,
//                List.of(new OrderEntry("prod1", 2, 50.0)));
//
//        // Mock customer entity
//        customerEntity = new CustomerEntity();
//        customerEntity.setId(1L);
//        customerEntity.setEmail("customer@example.com");
//    }
//
//    @Test
//    void testPlaceOrder_Successful() {
//        // Arrange
//        when(customerRepository.findById(orderContext.getCustomerReference())).thenReturn(Optional.of(customerEntity));
//
//        PaymentTransaction paymentTransaction = new PaymentTransaction(UUID.randomUUID(), "completed", 100.0, orderContext.getCartId());
//        when(paymentService.processPayment(any())).thenReturn(paymentTransaction);
//
//        // Act
//        Order order = orderService.placeOrder(orderContext);
//
//        // Assert
//        assertNotNull(order);
//        assertEquals(orderContext.getCartId(), order.getCartId());
//        assertEquals(orderContext.getTotalPrice(), order.getTotalPrice());
//        assertEquals(customerEntity.getId(), order.getConsumerReference());
//        verify(paymentService).processPayment(any()); // Verify that paymentService.processPayment was called
//    }
//
//    @Test
//    void testPlaceOrder_CustomerNotFound() {
//        // Arrange
//        when(customerRepository.findById(orderContext.getCustomerReference())).thenReturn(Optional.empty());
//
//        // Act & Assert
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> orderService.placeOrder(orderContext));
//        assertEquals("Customer not found", exception.getMessage());
//    }
//
//    @Test
//    void testPlaceOrder_PaymentFailed() {
//        // Arrange
//        when(customerRepository.findById(orderContext.getCustomerReference())).thenReturn(Optional.of(customerEntity));
//
//        PaymentTransaction paymentTransaction = new PaymentTransaction(UUID.randomUUID(), "failed", 100.0, orderContext.getCartId());
//        when(paymentService.processPayment(any())).thenReturn(paymentTransaction);
//
//        // Act & Assert
//        PaymentTransactionFailed exception = assertThrows(PaymentTransactionFailed.class, () -> orderService.placeOrder(orderContext));
//        assertEquals(paymentTransaction.getId(), exception.getTransactionId());
//        assertEquals(orderContext.getCartId(), exception.getCartId());
//    }
//}
