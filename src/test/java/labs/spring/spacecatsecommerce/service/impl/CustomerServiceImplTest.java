package labs.spring.spacecatsecommerce.service.impl;

import labs.spring.spacecatsecommerce.domain.CustomerDetails;
import labs.spring.spacecatsecommerce.repository.CustomerRepository;
import labs.spring.spacecatsecommerce.repository.entity.CustomerEntity;
import labs.spring.spacecatsecommerce.service.CustomerService;
import labs.spring.spacecatsecommerce.service.exception.CustomerNotFoundException;
import labs.spring.spacecatsecommerce.common.CommunicationChannel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Test
    void testGetAllCustomerDetails() {
        // Arrange
        List<CustomerEntity> mockEntities = List.of(
                CustomerEntity.builder().id(1L).name("Lucas").build(),
                CustomerEntity.builder().id(2L).name("Lyasa").build()
        );

        List<CustomerDetails> expectedDetails = mockEntities.stream()
                .map(entity -> CustomerDetails.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .build())
                .toList();

        when(customerRepository.findAll()).thenReturn(mockEntities);

        // Act
        List<CustomerDetails> actualDetails = customerServiceImpl.getAllCustomerDetails();

        // Assert
        assertNotNull(actualDetails);
        assertEquals(expectedDetails.size(), actualDetails.size());
        assertEquals(expectedDetails.get(0).getName(), actualDetails.get(0).getName());
        assertEquals(expectedDetails.get(1).getName(), actualDetails.get(1).getName());
    }


//    @Test
//    void testGetCustomerDetailsById_Success() {
//        // Arrange
//        Long customerId = 1L;
//        CustomerEntity mockEntity = CustomerEntity.builder()
//                .id(customerId)
//                .name("Alice")
//                .address("123 Wonderland")
//                .phoneNumber("555-1234")
//                .email("alice@example.com")
//                .communicationChannel(List.of(CommunicationChannel.MOBILE))
//                .build();
//
//        // Мок репозиторію для повернення знайденого клієнта
//        when(customerRepository.findById(customerId)).thenReturn(Optional.of(mockEntity));
//
//        // Act
//        CustomerDetails foundCustomer = customerServiceImpl.getCustomerDetailsById(customerId);
//
//        // Assert
//        assertNotNull(foundCustomer);
//        assertEquals("Alice", foundCustomer.getName());
//        assertEquals(customerId, foundCustomer.getId());
//        assertEquals("123 Wonderland", foundCustomer.getAddress());
//        assertEquals("555-1234", foundCustomer.getPhoneNumber());
//        assertEquals("alice@example.com", foundCustomer.getEmail());
//        assertTrue(foundCustomer.getPreferredChannel().contains(CommunicationChannel.MOBILE));
//    }
//


    @Test
    void testGetCustomerDetailsById_CustomerNotFound() {
        // Arrange
        Long customerId = 999L;

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // Act & Assert
        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () ->
                customerServiceImpl.getCustomerDetailsById(customerId)
        );
        assertEquals("Customer with id 999 not found", exception.getMessage());
    }

//    @Test
//    void testCreateCustomer() {
//        // Arrange
//        CustomerDetails newCustomerDetails = CustomerDetails.builder()
//                .name("Diana Prince")
//                .address("Amazonian Isle")
//                .phoneNumber("555-999-1111")
//                .email("diana@example.com")
//                .preferredChannel(List.of(CommunicationChannel.MOBILE))
//                .build();
//
//        // Перетворюємо CustomerDetails на CustomerEntity
//        CustomerEntity newCustomerEntity = CustomerEntity.builder()
//                .name("Diana Prince")
//                .address("Amazonian Isle")
//                .phoneNumber("555-999-1111")
//                .email("diana@example.com")
//                .communicationChannel(List.of(CommunicationChannel.MOBILE))
//                .build();
//
//        // Мокування поведінки save() для CustomerEntity
//        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(newCustomerEntity);
//
//        // Act
//        CustomerDetails createdCustomer = customerServiceImpl.createCustomer(newCustomerDetails);
//
//        // Assert
//        assertNotNull(createdCustomer);
//        assertEquals("Diana Prince", createdCustomer.getName());
//        assertEquals("Amazonian Isle", createdCustomer.getAddress());
//        assertEquals("555-999-1111", createdCustomer.getPhoneNumber());
//        assertEquals("diana@example.com", createdCustomer.getEmail());
//        assertTrue(createdCustomer.getPreferredChannel().contains(CommunicationChannel.MOBILE));
//    }

//    @Test
//    void testUpdateCustomer() {
//        // Arrange
//        Long customerId = 1L;
//        CustomerDetails updatedDetails = CustomerDetails.builder()
//                .name("Alice Johnson Updated")
//                .address("456 New Cosmic Lane")
//                .phoneNumber("123-789-4560")
//                .email("aliceupdated@example.com")
//                .preferredChannel(List.of(CommunicationChannel.MOBILE))
//                .build();
//
//        CustomerDetails existingCustomer = CustomerDetails.builder().id(customerId).name("Alice").build();
//
//        when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
//        when(customerRepository.save(any(CustomerDetails.class))).thenReturn(updatedDetails);
//
//        // Act
//        CustomerDetails updatedCustomer = customerServiceImpl.updateCustomer(customerId, updatedDetails);
//
//        // Assert
//        assertNotNull(updatedCustomer);
//        assertEquals("Alice Johnson Updated", updatedCustomer.getName());
//        assertEquals("456 New Cosmic Lane", updatedCustomer.getAddress());
//    }
}
