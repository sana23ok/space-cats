package labs.spring.spacecatsecommerce.domain.payment;

import java.util.UUID;
import labs.spring.spacecatsecommerce.common.PaymentStatus;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PaymentTransaction {

    UUID id;
    PaymentStatus status;
    String cartId;

}