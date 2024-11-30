package labs.spring.spacecatsecommerce;

import labs.spring.spacecatsecommerce.repository.CustomerRepository;
import labs.spring.spacecatsecommerce.repository.entity.CustomerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@ActiveProfiles("test")
class DatabaseConnectionTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testDatabaseConnection() {
        // Arrange: Ensure the database is populated with test data
        List<CustomerEntity> mockEntities = List.of(
                CustomerEntity.builder()
                        .name("Lucas")
                        .address("123 Space St")
                        .phoneNumber("123-456-7890")
                        .email("lucas@example.com")
                        //.customerReference(UUID.fromString("828d10a3-50cb-40af-b0d6-9b3338858087"))  // Ensure this field is set
                        .build(),
                CustomerEntity.builder()
                        .name("Lyasa")
                        .address("456 Cosmic Blvd")
                        .phoneNumber("987-654-3210")
                        .email("lyasa@example.com")
                        //.customerReference(UUID.fromString("4595b3ea-9df4-4214-b19d-335bf80a6fcf"))  // Ensure this field is set
                        .build()
        );
        customerRepository.saveAll(mockEntities);

        // Act: Retrieve the saved customer entities
        List<CustomerEntity> customers = customerRepository.findAll();

        // Assert
        assertFalse(customers.isEmpty(), "Database connection failed or no customers found");

        // Print the retrieved data for verification
        customers.forEach(customer -> {
            System.out.println("Customer Name: " + customer.getName());
            System.out.println("Customer Address: " + customer.getAddress());
            System.out.println("Customer Phone Number: " + customer.getPhoneNumber());
            System.out.println("Customer Email: " + customer.getEmail());
            //System.out.println("Customer Reference: " + customer.getCustomerReference());
            System.out.println("---------------------------");
        });
    }
}
