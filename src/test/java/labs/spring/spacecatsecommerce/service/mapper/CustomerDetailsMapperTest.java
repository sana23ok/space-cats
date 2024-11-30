//package labs.spring.spacecatsecommerce.service.mapper;
//
//import labs.spring.spacecatsecommerce.domain.CustomerDetails;
//import labs.spring.spacecatsecommerce.dto.customer.CustomerDetailsDto;
//import labs.spring.spacecatsecommerce.dto.customer.CustomerDetailsEntry;
//import labs.spring.spacecatsecommerce.common.CommunicationChannel;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//class CustomDetailsMapperTest {
//
//    private CustomDetailsMapper customDetailsMapper;
//
//    @BeforeEach
//    void setUp() {
//        customDetailsMapper = new CustomDetailsMapperImpl();
//    }
//
//    @Test
//    void toCustomerDetailsDto_ShouldMapCustomerDetailsToCustomerDetailsDto() {
//        CustomerDetails customerDetails = CustomerDetails.builder()
//                .id(1L)
//                .name("Jane Doe")
//                .address("456 Galactic Ave")
//                .phoneNumber("+0987654321")
//                .email("jane.doe@example.com")
//                .preferredChannel(List.of(CommunicationChannel.EMAIL, CommunicationChannel.SMS)) // Changed this line
//                .build();
//
//        CustomerDetailsDto customerDetailsDto = customDetailsMapper.toCustomerDetailsDto(customerDetails);
//
//        assertThat(customerDetailsDto).isNotNull();
//        assertThat(customerDetailsDto.getName()).isEqualTo(customerDetails.getName());
//        assertThat(customerDetailsDto.getAddress()).isEqualTo(customerDetails.getAddress());
//        assertThat(customerDetailsDto.getPhoneNumber()).isEqualTo(customerDetails.getPhoneNumber());
//        assertThat(customerDetailsDto.getEmail()).isEqualTo(customerDetails.getEmail());
//        assertThat(customerDetailsDto.getPreferredChannel()).containsExactlyInAnyOrder("email", "sms");
//    }
//
//
//    @Test
//    void toCustomerDetailsDto_NullCustomerDetails_ShouldReturnNull() {
//        CustomerDetailsDto customerDetailsDto = customDetailsMapper.toCustomerDetailsDto(null);
//        assertThat(customerDetailsDto).isNull();
//    }
//
//
//    @Test
//    void toCustomerDetailsEntry_NullList_ShouldReturnNull() {
//        List<CustomerDetailsEntry> entries = customDetailsMapper.toCustomerDetailsEntry(null);
//        assertThat(entries).isNull();
//    }
//
//    @Test
//    void toCustomerDetailsEntry_EmptyList_ShouldReturnEmptyList() {
//        List<CustomerDetailsEntry> entries = customDetailsMapper.toCustomerDetailsEntry(Collections.emptyList());
//        assertThat(entries).isNotNull().isEmpty();
//    }
//}
