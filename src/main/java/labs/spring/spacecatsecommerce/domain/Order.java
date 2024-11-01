package labs.spring.spacecatsecommerce.domain;

import labs.spring.spacecatsecommerce.common.ProductType;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Order {

    List<ProductType> productTypes;
    Double price;
    Long consumerReference;

}
