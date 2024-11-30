package labs.spring.spacecatsecommerce.service.impl;

import labs.spring.spacecatsecommerce.common.CommunicationChannel;
import labs.spring.spacecatsecommerce.domain.CustomerDetails;
import labs.spring.spacecatsecommerce.repository.CustomerRepository;
import labs.spring.spacecatsecommerce.repository.entity.CustomerEntity;
import labs.spring.spacecatsecommerce.service.CustomerService;
import labs.spring.spacecatsecommerce.service.exception.CustomerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDetails> getAllCustomerDetails() {
        List<CustomerEntity> customerEntities = StreamSupport
                .stream(customerRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return customerEntities.stream()
                .map(this::convertToCustomerDetails)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDetails getCustomerDetailsById(Long customerId) {
        CustomerEntity customerEntity = customerRepository.findById(customerId)
                .orElseThrow(() -> {
                    log.info("Customer with id {} not found", customerId);
                    return new CustomerNotFoundException(customerId);
                });
        return convertToCustomerDetails(customerEntity);
    }

    @Override
    @Transactional
    public CustomerDetails createCustomer(CustomerDetails customerDetails) {
        CustomerEntity customerEntity = convertToCustomerEntity(customerDetails);
        CustomerEntity savedCustomerEntity = customerRepository.save(customerEntity);
        CustomerDetails savedCustomerDetails = convertToCustomerDetails(savedCustomerEntity);
        log.info("Customer created: {}", savedCustomerDetails);
        return savedCustomerDetails;
    }

    @Override
    @Transactional
    public CustomerDetails updateCustomer(Long customerId, CustomerDetails updatedDetails) {
        CustomerEntity existingCustomerEntity = customerRepository.findById(customerId)
                .orElseThrow(() -> {
                    log.info("Customer with id {} not found", customerId);
                    return new CustomerNotFoundException(customerId);
                });

        existingCustomerEntity.setName(updatedDetails.getName() != null ? updatedDetails.getName() : existingCustomerEntity.getName());
        existingCustomerEntity.setAddress(updatedDetails.getAddress() != null ? updatedDetails.getAddress() : existingCustomerEntity.getAddress());
        existingCustomerEntity.setPhoneNumber(updatedDetails.getPhoneNumber() != null ? updatedDetails.getPhoneNumber() : existingCustomerEntity.getPhoneNumber());
        existingCustomerEntity.setEmail(updatedDetails.getEmail() != null ? updatedDetails.getEmail() : existingCustomerEntity.getEmail());


        CustomerEntity savedUpdatedCustomerEntity = customerRepository.save(existingCustomerEntity);
        CustomerDetails updatedCustomerDetails = convertToCustomerDetails(savedUpdatedCustomerEntity);
        log.info("Customer updated: {}", updatedCustomerDetails);
        return updatedCustomerDetails;
    }

    private CustomerEntity convertToCustomerEntity(CustomerDetails customerDetails) {
        // Example conversion logic
        return CustomerEntity.builder()
                .id(customerDetails.getId())
                .name(customerDetails.getName())
                .address(customerDetails.getAddress())
                .phoneNumber(customerDetails.getPhoneNumber())
                .email(customerDetails.getEmail())
                .build();
    }

    private CustomerDetails convertToCustomerDetails(CustomerEntity customerEntity) {
        // Example conversion logic
        return CustomerDetails.builder()
                .id(customerEntity.getId())
                .name(customerEntity.getName())
                .address(customerEntity.getAddress())
                .phoneNumber(customerEntity.getPhoneNumber())
                .email(customerEntity.getEmail())
                .build();
    }
}
