package labs.spring.spacecatsecommerce.domain;

import labs.spring.spacecatsecommerce.common.ProductType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Product {

    private Long id;
    private String name;
    private ProductType type;
    private Double price;
    private String description;

}
