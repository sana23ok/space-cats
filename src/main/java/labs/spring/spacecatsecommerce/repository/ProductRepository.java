package labs.spring.spacecatsecommerce.repository;

import labs.spring.spacecatsecommerce.repository.entity.ProductEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByName(String name);

    List<ProductEntity> findByNameAndIdIsNot(String name, Long id);

}
