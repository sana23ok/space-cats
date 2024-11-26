package labs.spring.spacecatsecommerce.domain.order;

import labs.spring.spacecatsecommerce.common.ProductType;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class OrderEntry {

    ProductType productType;
    int amount;

}