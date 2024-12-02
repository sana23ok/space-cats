package labs.spring.spacecatsecommerce.domain.order;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Order {

    Long id;
    List<OrderEntry> entries;
    Long customer;
    double totalPrice;
}
