package labs.spring.spacecatsecommerce.service.mapper;

import labs.spring.spacecatsecommerce.dto.customer.CustomerDetailsDto;
import labs.spring.spacecatsecommerce.dto.customer.CustomerDetailsEntry;
import labs.spring.spacecatsecommerce.dto.customer.CustomerDetailsListDto;
import labs.spring.spacecatsecommerce.domain.CustomerDetails;
import labs.spring.spacecatsecommerce.repository.entity.CustomerEntity;
import labs.spring.spacecatsecommerce.common.CommunicationChannel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerDetailsMapperTest {

    private CustomerDetailsMapper customerDetailsMapper;

    @BeforeEach
    void setUp() {
        customerDetailsMapper = Mappers.getMapper(CustomerDetailsMapper.class);
    }

    @Test
    void dtoToCustomerEntity_ShouldMapCustomerDetailsDtoToCustomerEntity() {
        CustomerDetailsDto customerDetailsDto = CustomerDetailsDto.builder()
                .id(1L)
                .name("John Doe")
                .address("123 Space Street")
                .phoneNumber("+1234567890")
                .email("john.doe@example.com")
                .preferredChannel(Arrays.asList("email", "sms"))
                .build();

        CustomerEntity customerEntity = customerDetailsMapper.dtoToCustomerEntity(customerDetailsDto);

        assertThat(customerEntity).isNotNull();
        assertThat(customerEntity.getId()).isEqualTo(customerDetailsDto.getId());
        assertThat(customerEntity.getName()).isEqualTo(customerDetailsDto.getName());
        assertThat(customerEntity.getAddress()).isEqualTo(customerDetailsDto.getAddress());
        assertThat(customerEntity.getPhoneNumber()).isEqualTo(customerDetailsDto.getPhoneNumber());
        assertThat(customerEntity.getEmail()).isEqualTo(customerDetailsDto.getEmail());
        assertThat(customerEntity.getCommunicationChannel())
                .containsExactlyInAnyOrder(CommunicationChannel.EMAIL, CommunicationChannel.SMS);
    }

    @Test
    void entityToCustomerDetailsDto_ShouldMapCustomerEntityToCustomerDetailsDto() {
        CustomerEntity customerEntity = CustomerEntity.builder()
                .id(2L)
                .name("Jane Smith")
                .address("456 Cosmic Ave")
                .phoneNumber("+0987654321")
                .email("jane.smith@example.com")
                .communicationChannel(Arrays.asList(CommunicationChannel.SMS, CommunicationChannel.EMAIL))
                .build();

        CustomerDetailsDto customerDetailsDto = customerDetailsMapper.entityToCustomerDetailsDto(customerEntity);

        assertThat(customerDetailsDto).isNotNull();
        assertThat(customerDetailsDto.getId()).isEqualTo(customerEntity.getId());
        assertThat(customerDetailsDto.getName()).isEqualTo(customerEntity.getName());
        assertThat(customerDetailsDto.getAddress()).isEqualTo(customerEntity.getAddress());
        assertThat(customerDetailsDto.getPhoneNumber()).isEqualTo(customerEntity.getPhoneNumber());
        assertThat(customerDetailsDto.getEmail()).isEqualTo(customerEntity.getEmail());
        assertThat(customerDetailsDto.getPreferredChannel())
                .containsExactlyInAnyOrder("sms", "email");
    }

    @Test
    void detailsToCustomerDetailsDto_ShouldMapCustomerDetailsToCustomerDetailsDto() {
        CustomerDetails customerDetails = CustomerDetails.builder()
                .id(3L)
                .name("Alice Johnson")
                .address("789 Lunar Blvd")
                .phoneNumber("+1122334455")
                .email("alice.johnson@example.com")
                .preferredChannel(Arrays.asList(CommunicationChannel.MOBILE, CommunicationChannel.EMAIL))
                .build();

        CustomerDetailsDto customerDetailsDto = customerDetailsMapper.detailsToCustomerDetailsDto(customerDetails);

        assertThat(customerDetailsDto).isNotNull();
        assertThat(customerDetailsDto.getId()).isEqualTo(customerDetails.getId());
        assertThat(customerDetailsDto.getName()).isEqualTo(customerDetails.getName());
        assertThat(customerDetailsDto.getAddress()).isEqualTo(customerDetails.getAddress());
        assertThat(customerDetailsDto.getPhoneNumber()).isEqualTo(customerDetails.getPhoneNumber());
        assertThat(customerDetailsDto.getEmail()).isEqualTo(customerDetails.getEmail());
        assertThat(customerDetailsDto.getPreferredChannel())
                .containsExactlyInAnyOrder("mobile", "email");
    }

    @Test
    void toCustomerDetailsListDto_ShouldMapListOfCustomerDetailsToCustomerDetailsListDto() {
        CustomerDetails customerDetails1 = CustomerDetails.builder()
                .id(4L)
                .name("Bob Marley")
                .address("101 Ocean Drive")
                .phoneNumber("+9998887777")
                .email("bob.marley@example.com")
                .preferredChannel(Arrays.asList(CommunicationChannel.MOBILE))
                .build();

        CustomerDetails customerDetails2 = CustomerDetails.builder()
                .id(5L)
                .name("Charlie Brown")
                .address("202 Sunset Blvd")
                .phoneNumber("+5566778899")
                .email("charlie.brown@example.com")
                .preferredChannel(Arrays.asList(CommunicationChannel.SMS))
                .build();

        List<CustomerDetails> customerDetailsList = Arrays.asList(customerDetails1, customerDetails2);

        CustomerDetailsListDto customerDetailsListDto = customerDetailsMapper.toCustomerDetailsListDto(customerDetailsList);

        assertThat(customerDetailsListDto).isNotNull();
        assertThat(customerDetailsListDto.getCustomerDetailsEntries()).hasSize(2);

        CustomerDetailsEntry entry1 = customerDetailsListDto.getCustomerDetailsEntries().get(0);
        assertThat(entry1.getId()).isEqualTo(customerDetails1.getId());
        assertThat(entry1.getName()).isEqualTo(customerDetails1.getName());

        CustomerDetailsEntry entry2 = customerDetailsListDto.getCustomerDetailsEntries().get(1);
        assertThat(entry2.getId()).isEqualTo(customerDetails2.getId());
        assertThat(entry2.getName()).isEqualTo(customerDetails2.getName());
    }

    @Test
    void toPreferredChannelEnum_ShouldConvertStringListToEnumList() {
        List<String> preferredChannel = Arrays.asList("email", "sms");
        List<CommunicationChannel> communicationChannels = customerDetailsMapper.toPreferredChannelEnum(preferredChannel);

        assertThat(communicationChannels).hasSize(2);
        assertThat(communicationChannels).containsExactlyInAnyOrder(CommunicationChannel.EMAIL, CommunicationChannel.SMS);
    }

    @Test
    void toPreferredChannelString_ShouldConvertEnumListToStringList() {
        List<CommunicationChannel> communicationChannels = Arrays.asList(CommunicationChannel.MOBILE, CommunicationChannel.SMS);
        List<String> preferredChannel = customerDetailsMapper.toPreferredChannelString(communicationChannels);

        assertThat(preferredChannel).hasSize(2);
        assertThat(preferredChannel).containsExactlyInAnyOrder("mobile", "sms");
    }

    @Test
    void toCustomerDetailsEntry_ShouldMapCustomerDetailsToCustomerDetailsEntry() {
        CustomerDetails customerDetails = CustomerDetails.builder()
                .id(6L)
                .name("David Lee")
                .address("303 Mountain Rd")
                .phoneNumber("+1122334455")
                .email("david.lee@example.com")
                .preferredChannel(Arrays.asList(CommunicationChannel.SMS))
                .build();

        CustomerDetailsEntry customerDetailsEntry = customerDetailsMapper.toCustomerDetailsEntry(customerDetails);

        assertThat(customerDetailsEntry).isNotNull();
        assertThat(customerDetailsEntry.getId()).isEqualTo(customerDetails.getId());
        assertThat(customerDetailsEntry.getName()).isEqualTo(customerDetails.getName());
        assertThat(customerDetailsEntry.getAddress()).isEqualTo(customerDetails.getAddress());
        assertThat(customerDetailsEntry.getPhoneNumber()).isEqualTo(customerDetails.getPhoneNumber());
        assertThat(customerDetailsEntry.getEmail()).isEqualTo(customerDetails.getEmail());
    }
}
