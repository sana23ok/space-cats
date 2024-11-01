package labs.spring.spacecatsecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import labs.spring.spacecatsecommerce.common.ProductType;
import labs.spring.spacecatsecommerce.dto.validation.CosmicWordCheck;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class ProductDTO {
    @NotNull(message = "Product ID cannot be null")
    private Long id;

    @CosmicWordCheck
    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 1, max = 50, message = "Product name must be between 1 and 50 characters")
    private String name;

    @NotNull(message = "Product type is required")
    private ProductType type;

    @NotNull(message = "Product price cannot be null")
    @Min(value = 0, message = "Product price must be a positive value")
    private Double price;

    @Size(max = 200, message = "Description must be no more than 200 characters")
    private String description;
}
