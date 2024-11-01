//package labs.spring.spacecatsecommerce.service.mapper;
//
//import labs.spring.spacecatsecommerce.domain.CustomerDetails;
//import labs.spring.spacecatsecommerce.common.CommunicationChannel;
//import labs.spring.spacecatsecommerce.dto.CustomerDetailsDto;
//import labs.spring.spacecatsecommerce.dto.CustomerDetailsEntry;
//import labs.spring.spacecatsecommerce.dto.CustomerDetailsListDto;
//import java.util.List;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Named;
//
//@Mapper(componentModel = "spring")
//public interface CustomDetailsMapper {
//
////    @Mapping(target = "name", source = "name")
////    @Mapping(target = "address", source = "address")
////    @Mapping(target = "phoneNumber", source = "phoneNumber")
////    @Mapping(target = "email", source = "email")
////    @Mapping(target = "preferredChannel", source = "preferredChannel", qualifiedByName = "toPreferredChannelString")
//    CustomerDetailsDto toCustomerDetailsDto(CustomerDetails customerDetails);
//
//    default CustomerDetailsListDto toCustomerDetailsListDto(List<CustomerDetails> customerDetails) {
//        return CustomerDetailsListDto.builder().customerDetailsEntries(toCustomerDetailsEntry(customerDetails)).build();
//    }
//
//    List<CustomerDetailsEntry> toCustomerDetailsEntry(List<CustomerDetails> customerDetails);
//
////    @Mapping(target = "id", source = "id")
////    @Mapping(target = "address", source = "address")
////    @Mapping(target = "phoneNumber", source = "phoneNumber")
////    @Mapping(target = "email", source = "email")
////    @Mapping(target = "preferredChannel", source = "preferredChannel", qualifiedByName = "toPreferredChannelString")
//    CustomerDetailsEntry toCustomerDetailsEntry(CustomerDetails customerDetails);
//
////    @Named("toPreferredChannelString")
//    default List<String> toPreferredChannelString(List<CommunicationChannel> preferredChannel) {
//        return preferredChannel.stream().map(channel -> channel.name().toLowerCase()).toList();
//    }
//
//}

package labs.spring.spacecatsecommerce.service.mapper;

import labs.spring.spacecatsecommerce.domain.CustomerDetails;
import labs.spring.spacecatsecommerce.dto.CustomerDetailsDto;
import labs.spring.spacecatsecommerce.dto.CustomerDetailsEntry;
import labs.spring.spacecatsecommerce.dto.CustomerDetailsListDto;
import labs.spring.spacecatsecommerce.common.CommunicationChannel;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CustomDetailsMapper {

    default CustomerDetailsDto toCustomerDetailsDto(CustomerDetails customerDetails) {
        if (customerDetails == null) {
            return null;
        }

        return CustomerDetailsDto.builder()
//                .id(customerDetails.getId())
                .name(customerDetails.getName())
                .address(customerDetails.getAddress())
                .phoneNumber(customerDetails.getPhoneNumber())
                .email(customerDetails.getEmail())
                .preferredChannel(toPreferredChannelString(customerDetails.getPreferredChannel()))
                .build();
    }

    default CustomerDetailsListDto toCustomerDetailsListDto(List<CustomerDetails> customerDetails) {
        return CustomerDetailsListDto.builder()
                .customerDetailsEntries(toCustomerDetailsEntry(customerDetails))
                .build();
    }

    default List<CustomerDetailsEntry> toCustomerDetailsEntry(List<CustomerDetails> customerDetails) {
        if (customerDetails == null) {
            return null;
        }

        return customerDetails.stream().map(details -> CustomerDetailsEntry.builder()
                .id(details.getId())
                .name(details.getName())
                .address(details.getAddress())
                .phoneNumber(details.getPhoneNumber())
                .email(details.getEmail())
                .preferredChannel(toPreferredChannelString(details.getPreferredChannel()))
                .build()).collect(Collectors.toList());
    }

    default List<String> toPreferredChannelString(List<CommunicationChannel> preferredChannel) {
        if (preferredChannel == null) {
            return null;
        }

        return preferredChannel.stream()
                .map(channel -> channel.name().toLowerCase())
                .collect(Collectors.toList());
    }
}
