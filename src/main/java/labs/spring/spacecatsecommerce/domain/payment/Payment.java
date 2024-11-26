package labs.spring.spacecatsecommerce.domain.payment;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Payment {

    String consumerReference;
    String cartId;
    UUID paymentAssetId;
    double amount;

}