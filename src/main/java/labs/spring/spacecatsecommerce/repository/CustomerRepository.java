package labs.spring.spacecatsecommerce.repository;

import labs.spring.spacecatsecommerce.repository.entity.CustomerEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {}
