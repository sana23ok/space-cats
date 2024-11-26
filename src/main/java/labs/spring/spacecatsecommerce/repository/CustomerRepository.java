package labs.spring.spacecatsecommerce.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import labs.spring.spacecatsecommerce.repository.entity.CustomerEntity;
import labs.spring.spacecatsecommerce.repository.projection.CustomerDetailsProjection;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends NaturalIdRepository<CustomerEntity, UUID> {

    Optional<CustomerEntity> findDistinctByPhoneNumberOrderByNameAsc(String phoneNumber);

    Optional<CustomerDetailsProjection> findDistinctByEmail(String email);
}
