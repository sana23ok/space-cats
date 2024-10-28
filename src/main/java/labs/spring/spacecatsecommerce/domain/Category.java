package labs.spring.spacecatsecommerce.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Category {
    Long id;
    String name;
    String description;
}

