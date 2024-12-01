package labs.spring.spacecatsecommerce.service.impl;

import labs.spring.spacecatsecommerce.AbstractIT;
import labs.spring.spacecatsecommerce.domain.CustomerDetails;
import labs.spring.spacecatsecommerce.dto.customer.CustomerDetailsDto;
import labs.spring.spacecatsecommerce.repository.CustomerRepository;
import labs.spring.spacecatsecommerce.repository.entity.CustomerEntity;
import labs.spring.spacecatsecommerce.service.CustomerService;
import labs.spring.spacecatsecommerce.service.exception.CustomerNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Testcontainers
@DisplayName("CustomerService Tests with Testcontainers")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerServiceImplIT extends AbstractIT {

    @Autowired
    private CustomerService customerService;

    @SpyBean
    @Autowired
    private CustomerRepository customerRepository;

    private static Long newCustomerId;
    private static String newCustomerName = "Test Customer";
    private static String updatedCustomerName = "Updated Customer";

    @AfterEach
    void cleanUp() {
        customerRepository.deleteAll();
    }

    @BeforeEach
    void setUp() {
        reset(customerRepository);

        CustomerEntity customer =
                customerRepository.save(CustomerEntity.builder()
                        .name("John Doe")
                        .address("123 Main St")
                        .email("john@example.com")
                        .phoneNumber("555-5555")
                        .build());

        newCustomerId = customer.getId();
    }

    @Test
    @Order(1)
    void testGetAllCustomerDetails() {
        List<CustomerDetails> customerDetailsList = customerService.getAllCustomerDetails();
        assertNotNull(customerDetailsList);
        assertFalse(customerDetailsList.isEmpty());
    }

    @Test
    @Order(2)
    void testGetCustomerById() {
        CustomerDetails customerDetails = customerService.getCustomerDetailsById(newCustomerId);
        assertNotNull(customerDetails);
        assertEquals(newCustomerId, customerDetails.getId());
        assertEquals("John Doe", customerDetails.getName());
    }

    @Test
    @Order(3)
    void testCreateCustomer() {
        CustomerDetailsDto newCustomer =
                CustomerDetailsDto.builder()
                        .name(newCustomerName)
                        .address("456 Elm St")
                        .email("testcustomer@example.com")
                        .phoneNumber("555-1234")
                        .build();

        CustomerDetailsDto createdCustomer = customerService.createCustomer(newCustomer);
        assertNotNull(createdCustomer);
        assertEquals(newCustomerName, createdCustomer.getName());

        CustomerEntity savedCustomerEntity = customerRepository.findById(createdCustomer.getId()).orElseThrow();
        assertEquals(newCustomerName, savedCustomerEntity.getName());
    }

    @Test
    @Order(4)
    void testUpdateCustomer() {
        CustomerDetails updatedDetails = CustomerDetails.builder()
                .id(newCustomerId)
                .name(updatedCustomerName)
                .address("789 Oak St")
                .phoneNumber("555-9876")
                .email("updatedemail@example.com")
                .build();

        CustomerDetails updatedCustomer = customerService.updateCustomer(newCustomerId, updatedDetails);

        assertNotNull(updatedCustomer);
        assertEquals(updatedCustomerName, updatedCustomer.getName());
        assertEquals("789 Oak St", updatedCustomer.getAddress());
    }

    @Test
    @Order(5)
    void testCustomerNotFoundExceptionWhenGettingCustomerById() {
        Long invalidId = -1L;
        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerDetailsById(invalidId));
    }

    @Test
    @Order(6)
    void testCustomerNotFoundExceptionWhenUpdatingNonExistentCustomer() {
        Long invalidId = -1L;
        CustomerDetails updatedDetails = CustomerDetails.builder()
                .name(updatedCustomerName)
                .address("123 Test St")
                .build();

        assertThrows(CustomerNotFoundException.class, () -> customerService.updateCustomer(invalidId, updatedDetails));
    }
}

