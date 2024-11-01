package labs.spring.spacecatsecommerce.domain;

import labs.spring.spacecatsecommerce.common.CommunicationChannel;
import java.util.List;

import lombok.Value;
import lombok.Builder;
import lombok.experimental.Accessors;

@Value
@Builder(toBuilder = true)
public class CustomerDetails {

    Long id;
    String name;
    String address;
    String phoneNumber;
    String email;
    List<CommunicationChannel> preferredChannel;

}