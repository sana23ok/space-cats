package labs.spring.spacecatsecommerce.dto;

import labs.spring.spacecatsecommerce.common.ProductType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    Long id;
    String name;
    ProductType type;
    Double price;
    String description;
}

