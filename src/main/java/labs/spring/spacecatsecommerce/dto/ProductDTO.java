package labs.spring.spacecatsecommerce.dto;

import labs.spring.spacecatsecommerce.common.ProductType;
import lombok.Data;

@Data
public class ProductDTO {
    Long id;
    String name;
    ProductType type;
    Double price;
    String description;
}

