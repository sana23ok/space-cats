package labs.spring.spacecatsecommerce.domain.order;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Order {

    String id;
    UUID transactionId;
    List<OrderEntry> entries;
    String cartId;
    Long consumerReference;
    Double totalPrice;

}