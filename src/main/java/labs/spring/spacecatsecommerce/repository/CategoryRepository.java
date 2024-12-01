package labs.spring.spacecatsecommerce.repository;

import labs.spring.spacecatsecommerce.repository.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {}
