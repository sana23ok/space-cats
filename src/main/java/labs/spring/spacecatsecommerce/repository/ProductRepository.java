package labs.spring.spacecatsecommerce.repository;

import labs.spring.spacecatsecommerce.repository.entity.ProductEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    List<ProductEntity> findByName(String name);

    List<ProductEntity> findByNameAndIdIsNot(String name, Long id);

}
