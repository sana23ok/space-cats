package labs.spring.spacecatsecommerce.dto.customer;

import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class CustomerDetailsListDto {
    List<CustomerDetailsEntry> customerDetailsEntries;
}
