package labs.spring.spacecatsecommerce.domain.order;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class OrderEntry {
    Long id;
    String productCode;
    int quantity;
    double price;
}
