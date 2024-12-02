package labs.spring.spacecatsecommerce.dto.order;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class OrderDto {
    private Long id;
    private Long customerId;
    private List<OrderEntryDto> entries;
    private Double totalPrice;
}

