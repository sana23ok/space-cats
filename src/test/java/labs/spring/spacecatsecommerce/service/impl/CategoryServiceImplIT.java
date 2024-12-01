package labs.spring.spacecatsecommerce.service.impl;

import labs.spring.spacecatsecommerce.AbstractIT;
import labs.spring.spacecatsecommerce.dto.CategoryDTO;
import labs.spring.spacecatsecommerce.repository.CategoryRepository;
import labs.spring.spacecatsecommerce.repository.entity.CategoryEntity;
import labs.spring.spacecatsecommerce.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@DisplayName("CategoryService Tests with Testcontainers")
public class CategoryServiceImplIT extends AbstractIT {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        categoryRepository.deleteAll();
    }

    @Test
    @Transactional
    void testGetCategoryById() {
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .name("Electronics")
                .description("Devices and gadgets")
                .build();
        categoryRepository.save(categoryEntity);

        CategoryDTO categoryDTO = categoryService.getCategoryById(categoryEntity.getId());
        assertNotNull(categoryDTO);
        assertEquals("Electronics", categoryDTO.getName());
        assertEquals("Devices and gadgets", categoryDTO.getDescription());
    }

    @Test
    @Transactional
    void testGetAllCategories() {
        CategoryEntity category1 = CategoryEntity.builder()
                .name("Electronics")
                .description("Devices and gadgets")
                .build();
        CategoryEntity category2 = CategoryEntity.builder()
                .name("Toys")
                .description("Fun items for pets")
                .build();
        categoryRepository.save(category1);
        categoryRepository.save(category2);

        List<CategoryDTO> categories = categoryService.getAllCategories();
        assertNotNull(categories);
        assertEquals(2, categories.size());
    }

    @Test
    @Transactional
    void testCreateCategory() {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .name("Books")
                .description("Various kinds of books")
                .build();

        CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);
        assertNotNull(createdCategory);
        assertEquals("Books", createdCategory.getName());
        assertTrue(categoryRepository.existsById(createdCategory.getId()));
    }
}
