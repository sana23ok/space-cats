package labs.spring.spacecatsecommerce.web;

import labs.spring.spacecatsecommerce.dto.customer.CustomerDetailsDto;
import labs.spring.spacecatsecommerce.dto.customer.CustomerDetailsListDto;
import labs.spring.spacecatsecommerce.service.CustomerService;
import labs.spring.spacecatsecommerce.service.mapper.CustomerDetailsMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerDetailsMapper customerDetailsMapper;

    public CustomerController(CustomerService customerService, CustomerDetailsMapper customDetailsMapper) {
        this.customerService = customerService;
        this.customerDetailsMapper = customDetailsMapper;
    }

    @GetMapping
    public ResponseEntity<CustomerDetailsListDto> getAllCustomers() {
        return ResponseEntity.ok(
                customerDetailsMapper.toCustomerDetailsListDto(customerService.getAllCustomerDetails())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDetailsDto> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(
                customerDetailsMapper.detailsToCustomerDetailsDto(customerService.getCustomerDetailsById(id))
        );
    }

    @PostMapping
    public ResponseEntity<CustomerDetailsDto> createCustomer(@RequestBody @Valid CustomerDetailsDto customerDetailsDto){
        var createdCustomerDetails = customerService.createCustomer(customerDetailsDto);
        return ResponseEntity.ok(createdCustomerDetails);
    }

}
