package labs.spring.spacecatsecommerce.dto;

import java.util.List;
import lombok.Data;

@Data
public class OrderDTO {
    Long id;
    List<ProductDTO> products;
    Double totalPrice;
    Long consumerReference;
}
