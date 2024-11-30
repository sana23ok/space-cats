package labs.spring.spacecatsecommerce.service;

import labs.spring.spacecatsecommerce.domain.CustomerDetails;
import labs.spring.spacecatsecommerce.dto.customer.CustomerDetailsDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerService {

    List<CustomerDetails> getAllCustomerDetails();

    CustomerDetails getCustomerDetailsById(Long customerId);

    CustomerDetailsDto createCustomer(CustomerDetailsDto customerDetailsDto);

    CustomerDetails updateCustomer(Long customerId, CustomerDetails updatedDetails);
}
