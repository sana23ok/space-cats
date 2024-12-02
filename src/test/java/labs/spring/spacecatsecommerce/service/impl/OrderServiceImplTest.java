package labs.spring.spacecatsecommerce.service.impl;

import labs.spring.spacecatsecommerce.domain.order.Order;
import labs.spring.spacecatsecommerce.dto.order.OrderDto;
import labs.spring.spacecatsecommerce.repository.OrderEntryRepository;
import labs.spring.spacecatsecommerce.repository.OrderRepository;
import labs.spring.spacecatsecommerce.service.mapper.OrderMapper;
import labs.spring.spacecatsecommerce.repository.entity.OrderEntity;
import labs.spring.spacecatsecommerce.repository.entity.OrderEntryEntity;
import labs.spring.spacecatsecommerce.domain.Product;
import labs.spring.spacecatsecommerce.domain.Category;
import labs.spring.spacecatsecommerce.common.ProductType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderEntryRepository orderEntryRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder() {
        Order order = Order.builder()
                .totalPrice(100)
                .build();
        OrderEntity orderEntity = OrderEntity.builder().build();
        OrderEntity savedOrderEntity = OrderEntity.builder().id(1L).build();

        when(orderMapper.toOrderEntity(order)).thenReturn(orderEntity);
        when(orderRepository.save(orderEntity)).thenReturn(savedOrderEntity);
        when(orderMapper.toOrderDto(savedOrderEntity)).thenReturn(OrderDto.builder().id(1L).build());

        OrderDto orderDto = orderService.createOrder(order);

        assertNotNull(orderDto);
        assertEquals(1L, orderDto.getId());
        verify(orderRepository, times(1)).save(orderEntity);
    }

    @Test
    void testFindOrderById_Found() {
        Long orderId = 1L;
        OrderEntity orderEntity = OrderEntity.builder().id(orderId).build();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(orderEntity));
        when(orderMapper.toOrderDto(orderEntity)).thenReturn(OrderDto.builder().id(orderId).build());

        OrderDto orderDto = orderService.findOrderById(orderId);

        assertNotNull(orderDto);
        assertEquals(orderId, orderDto.getId());
    }

    @Test
    void testFindOrderById_NotFound() {
        Long orderId = 1L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> orderService.findOrderById(orderId));
        assertEquals("Order not found with ID: " + orderId, exception.getMessage());
    }

    @Test
    void testFindAllOrders() {
        OrderEntity orderEntity = OrderEntity.builder().id(1L).build();
        List<OrderEntity> orderEntities = List.of(orderEntity);

        when(orderRepository.findAll()).thenReturn(orderEntities);
        when(orderMapper.toOrderDto(orderEntity)).thenReturn(OrderDto.builder().id(1L).build());

        List<OrderDto> orderDtos = orderService.findAllOrders();

        assertNotNull(orderDtos);
        assertEquals(1, orderDtos.size());
        assertEquals(1L, orderDtos.get(0).getId());
    }

    @Test
    void testDeleteOrder_Success() {
        Long orderId = 1L;
        when(orderRepository.existsById(orderId)).thenReturn(true);
        orderService.deleteOrder(orderId);
        verify(orderRepository, times(1)).deleteById(orderId);
    }

    @Test
    void testDeleteOrder_NotFound() {
        Long orderId = 1L;

        when(orderRepository.existsById(orderId)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> orderService.deleteOrder(orderId));
        assertEquals("Order not found with ID: " + orderId, exception.getMessage());
    }

    @Test
    void testAddEntryToOrder() {
        Long orderId = 1L;
        Long productCode = 12345L;
        int quantity = 2;
        OrderEntity orderEntity = OrderEntity.builder().id(orderId).build();
        Product mockProduct = Product.builder()
                .id(productCode)
                .name("Laser Pointer Asteroid")
                .description("A high-quality laser pointer.")
                .price(15.99)
                .type(ProductType.COSMIC_CATNIP)
                .category(Category.builder()
                        .name("Electronics")
                        .description("Devices and gadgets")
                        .build())
                .build();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(orderEntity));
        when(orderEntryRepository.save(any(OrderEntryEntity.class))).thenReturn(OrderEntryEntity.builder().build());
        when(orderMapper.toOrderDto(orderEntity)).thenReturn(OrderDto.builder().id(orderId).build());

        OrderDto orderDto = orderService.addEntryToOrder(orderId, productCode, quantity);

        assertNotNull(orderDto);
        verify(orderEntryRepository, times(1)).save(any(OrderEntryEntity.class));
    }

}
