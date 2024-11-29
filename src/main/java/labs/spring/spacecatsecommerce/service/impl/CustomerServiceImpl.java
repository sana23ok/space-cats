package labs.spring.spacecatsecommerce.service.impl;

import labs.spring.spacecatsecommerce.common.CommunicationChannel;
import labs.spring.spacecatsecommerce.domain.CustomerDetails;
import labs.spring.spacecatsecommerce.service.CustomerService;
import labs.spring.spacecatsecommerce.service.exception.CustomerNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final List<CustomerDetails> customerDetails = buildAllCustomerDetailsMock();

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDetails> getAllCustomerDetails() {
        return customerDetails;
    }


    @Override
    @Transactional(readOnly = true)
    public CustomerDetails getCustomerDetailsById(Long customerId) {
        return Optional.of(customerDetails.stream()
                        .filter(details -> details.getId().equals(customerId)).findFirst())
                .get()
                .orElseThrow(() -> {
                    log.info("Customer with id {} not found in mock", customerId);
                    return new CustomerNotFoundException(customerId);
                });
    }

    @Transactional
    @Override
    public CustomerDetails createCustomer(CustomerDetails customerDetails) {
        Long newId = this.customerDetails.stream()
                .map(CustomerDetails::getId)
                .max(Long::compareTo)
                .orElse(0L) + 1;

        CustomerDetails newCustomer = customerDetails.toBuilder().id(newId).build();
        this.customerDetails.add(newCustomer);
        log.info("Customer created: {}", newCustomer);
        return newCustomer;
    }

    @Transactional
    @Override
    public CustomerDetails updateCustomer(Long customerId, CustomerDetails updatedDetails) {
        CustomerDetails existingCustomer = getCustomerDetailsById(customerId);

        CustomerDetails updatedCustomer = existingCustomer.toBuilder()
                .name(updatedDetails.getName() != null ? updatedDetails.getName() : existingCustomer.getName())
                .address(updatedDetails.getAddress() != null ? updatedDetails.getAddress() : existingCustomer.getAddress())
                .phoneNumber(updatedDetails.getPhoneNumber() != null ? updatedDetails.getPhoneNumber() : existingCustomer.getPhoneNumber())
                .email(updatedDetails.getEmail() != null ? updatedDetails.getEmail() : existingCustomer.getEmail())
                .preferredChannel(updatedDetails.getPreferredChannel() != null ? updatedDetails.getPreferredChannel() : existingCustomer.getPreferredChannel())
                .build();

        int index = this.customerDetails.indexOf(existingCustomer);
        this.customerDetails.set(index, updatedCustomer);

        log.info("Customer updated: {}", updatedCustomer);
        return updatedCustomer;
    }


    private List<CustomerDetails> buildAllCustomerDetailsMock() {
        return List.of(
            CustomerDetails.builder()
                .id(1L)
                .name("Alice Johnson")
                .address("123 Cosmic Lane, Catnip City")
                .phoneNumber("123-456-7890")
                .email("alice@example.com")
                .preferredChannel(List.of(CommunicationChannel.EMAIL,
                        CommunicationChannel.MOBILE))
                .build(),
            CustomerDetails.builder()
                .id(2L)
                .name("Bob Smith")
                .address("456 Galactic Blvd, Star Town")
                .phoneNumber("987-654-3210")
                .email("bob@example.com")
                .preferredChannel(List.of(CommunicationChannel.MOBILE))
                .build(),
            CustomerDetails.builder()
                .id(3L)
                .name("Charlie Brown")
                .address("789 Nebula Road, Space Village")
                .phoneNumber("555-123-4567")
                .email("charlie@example.com")
                .preferredChannel(List.of(CommunicationChannel.EMAIL))
                .build());
    }
}
