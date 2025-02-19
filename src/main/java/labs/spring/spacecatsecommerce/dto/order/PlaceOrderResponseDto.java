package labs.spring.spacecatsecommerce.dto.order;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class PlaceOrderResponseDto {

    String orderId;
    String transactionId;

}
