package labs.spring.spacecatsecommerce.repository;

import labs.spring.spacecatsecommerce.repository.entity.OrderEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderEntryRepository extends JpaRepository<OrderEntryEntity, Long> {

    List<OrderEntryEntity> findByOrderId(Long orderId);

}

