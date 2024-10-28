package labs.spring.spacecatsecommerce.service;

import labs.spring.spacecatsecommerce.domain.CustomerDetails;
import java.util.List;

public interface CustomerService {

    List<CustomerDetails> getAllCustomerDetails();

    CustomerDetails getCustomerDetailsById(Long customerId);
}
