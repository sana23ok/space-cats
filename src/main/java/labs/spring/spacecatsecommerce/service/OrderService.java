package labs.spring.spacecatsecommerce.service;

import labs.spring.spacecatsecommerce.domain.order.Order;
import labs.spring.spacecatsecommerce.dto.order.OrderDto;
import labs.spring.spacecatsecommerce.dto.order.PlaceOrderResponseDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderDto createOrder(Order order);
    void deleteOrder(Long id);
    //Order updateOrder(Long id, Order order);
    OrderDto findOrderById(Long id);
    List<OrderDto> findAllOrders();
    //PlaceOrderResponseDto createOrderResponse(Order order);
    OrderDto addEntryToOrder(Long orderId, Long productCode, int quantity);
}