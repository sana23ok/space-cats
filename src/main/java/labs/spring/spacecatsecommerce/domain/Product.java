package labs.spring.spacecatsecommerce.domain;

import labs.spring.spacecatsecommerce.common.ProductType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Product {

    Long id;
    String name;
    ProductType type;
    Double price;
    String description;

}
