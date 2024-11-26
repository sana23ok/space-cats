package labs.spring.spacecatsecommerce.service;

import labs.spring.spacecatsecommerce.domain.order.Order;
import labs.spring.spacecatsecommerce.domain.order.OrderContext;

public interface OrderService {

    Order placeOrder(OrderContext orderContext);

}