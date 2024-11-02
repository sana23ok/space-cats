package labs.spring.spacecatsecommerce.service.impl;

import labs.spring.spacecatsecommerce.domain.CustomerDetails;
import labs.spring.spacecatsecommerce.service.exception.CustomerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest {

    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl();
    }

    @Test
    void getAllCustomerDetails_shouldReturnAllCustomers() {
        // Arrange (pre-setup is done in setUp method)

        // Act
        List<CustomerDetails> result = customerService.getAllCustomerDetails();

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size()); // Assuming there are 3 customers in the mock
    }

    @Test
    void getCustomerDetailsById_existingId_shouldReturnCustomer() {
        // Arrange
        Long existingId = 1L;

        // Act
        CustomerDetails result = customerService.getCustomerDetailsById(existingId);

        // Assert
        assertNotNull(result);
        assertEquals(existingId, result.getId());
        assertEquals("Alice Johnson", result.getName());
    }
}

