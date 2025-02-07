package labs.spring.spacecatsecommerce.service;

import labs.spring.spacecatsecommerce.domain.order.Order;
import labs.spring.spacecatsecommerce.dto.order.OrderDto;
import java.util.List;

public interface OrderService {

    OrderDto createOrder(Order order);
    void deleteOrder(Long id);
    OrderDto findOrderById(Long id);
    List<OrderDto> findAllOrders();
    OrderDto addEntryToOrder(Long orderId, Long productCode, int quantity);

}