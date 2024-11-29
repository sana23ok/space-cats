package labs.spring.spacecatsecommerce.repository;

import labs.spring.spacecatsecommerce.repository.entity.OrderEntity;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Long> {}
