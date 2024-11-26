package labs.spring.spacecatsecommerce.domain.order;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderContext {

    String cartId;
    UUID customerReference;
    List<OrderEntry> entries;
    Double totalPrice;

}
