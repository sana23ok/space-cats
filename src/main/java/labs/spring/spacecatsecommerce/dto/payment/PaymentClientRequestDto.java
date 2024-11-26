package labs.spring.spacecatsecommerce.dto.payment;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class PaymentClientRequestDto {

    String consumerReference;
    UUID paymentAssetId;
    double amount;

}
