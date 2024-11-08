package labs.spring.spacecatsecommerce.dto;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDTO {
    @NotNull(message = "Order ID cannot be null")
    private Long id;

    @NotEmpty(message = "Order must contain at least one product")
    private List<@NotNull(message = "Product cannot be null") ProductDTO> products;

    @NotNull(message = "Total price cannot be null")
    @Min(value = 0, message = "Total price must be at least 0")
    private Double totalPrice;

    private Long consumerReference;
}
