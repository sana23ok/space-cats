package labs.spring.spacecatsecommerce.dto.payment;

import java.util.UUID;

import labs.spring.spacecatsecommerce.common.PaymentStatus;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class PaymentClientResponseDto {

    UUID uuid;
    PaymentStatus status;
    String consumerReference;

}