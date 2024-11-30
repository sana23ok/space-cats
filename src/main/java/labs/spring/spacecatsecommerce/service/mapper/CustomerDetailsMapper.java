package labs.spring.spacecatsecommerce.service.mapper;

import labs.spring.spacecatsecommerce.dto.customer.CustomerDetailsDto;
import labs.spring.spacecatsecommerce.dto.customer.CustomerDetailsEntry;
import labs.spring.spacecatsecommerce.dto.customer.CustomerDetailsListDto;
import labs.spring.spacecatsecommerce.common.CommunicationChannel;
import labs.spring.spacecatsecommerce.domain.CustomerDetails;
import labs.spring.spacecatsecommerce.repository.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CustomerDetailsMapper {

    // Mapping from CustomerDetailsDto to CustomerEntity
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "communicationChannel", source = "preferredChannel", qualifiedByName = "toPreferredChannelEnum")
    CustomerEntity dtoToCustomerEntity(CustomerDetailsDto customerDetailsDto);

    // Mapping from CustomerEntity to CustomerDetailsDto
    @Mapping(target = "name", source = "name")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "preferredChannel", source = "communicationChannel", qualifiedByName = "toPreferredChannelString")
    CustomerDetailsDto entityToCustomerDetailsDto(CustomerEntity customerEntity);

    // Mapping from CustomerDetails to CustomerDetailsDto
    @Named("toCustomerDetailsDto")
    default CustomerDetailsDto detailsToCustomerDetailsDto(CustomerDetails customerDetails) {
        return CustomerDetailsDto.builder()
                .name(customerDetails.getName())
                .address(customerDetails.getAddress())
                .phoneNumber(customerDetails.getPhoneNumber())
                .email(customerDetails.getEmail())
                .preferredChannel(customerDetails.getPreferredChannel()
                        .stream()
                        .map(Enum::name)
                        .map(String::toLowerCase)
                        .collect(Collectors.toList()))
                .build();
    }

    // Mapping from List<CustomerDetails> to CustomerDetailsListDto
    @Named("toCustomerDetailsListDto")
    default CustomerDetailsListDto toCustomerDetailsListDto(List<CustomerDetails> customerDetailsList) {
        List<CustomerDetailsEntry> customerDetailsEntryList = customerDetailsList.stream()
                .map(this::toCustomerDetailsEntry) // Використовуємо метод toCustomerDetailsEntry
                .collect(Collectors.toList());
        return CustomerDetailsListDto.builder()
                .customerDetailsEntries(customerDetailsEntryList)
                .build();
    }

    @Named("toCustomerDetailsEntry")
    default CustomerDetailsEntry toCustomerDetailsEntry(CustomerDetails customerDetails) {
        return CustomerDetailsEntry.builder()
                .name(customerDetails.getName())
                .address(customerDetails.getAddress())
                .phoneNumber(customerDetails.getPhoneNumber())
                .email(customerDetails.getEmail())
                .build();
    }


    @Named("toPreferredChannelEnum")
    default List<CommunicationChannel> toPreferredChannelEnum(List<String> preferredChannel) {
        if (preferredChannel == null) {
            return null;
        }

        return preferredChannel.stream()
                .map(channel -> CommunicationChannel.valueOf(channel.toUpperCase()))
                .collect(Collectors.toList());
    }

    @Named("toPreferredChannelString")
    default List<String> toPreferredChannelString(List<CommunicationChannel> preferredChannel) {
        if (preferredChannel == null) {
            return null;
        }

        return preferredChannel.stream()
                .map(channel -> channel.name().toLowerCase())
                .collect(Collectors.toList());
    }
}
