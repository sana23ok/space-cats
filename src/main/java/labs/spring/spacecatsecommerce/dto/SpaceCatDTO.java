package labs.spring.spacecatsecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class SpaceCatDTO {
    @NotNull(message = "Product ID cannot be null")
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    private String name;

    @NotBlank(message = "Planet name cannot be blank")
    @Size(min = 1, max = 50, message = "Product name must be between 1 and 50 characters")
    private String planet;

    @Size(max = 100, message = "Breed name must be no more than 100 characters")
    private String breed;
}
