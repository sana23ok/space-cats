package labs.spring.spacecatsecommerce.service;

import labs.spring.spacecatsecommerce.domain.CustomerDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerService {

    List<CustomerDetails> getAllCustomerDetails();

    CustomerDetails getCustomerDetailsById(Long customerId);

    CustomerDetails createCustomer(CustomerDetails customerDetails);

    CustomerDetails updateCustomer(Long customerId, CustomerDetails updatedDetails);
}
