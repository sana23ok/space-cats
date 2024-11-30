package labs.spring.spacecatsecommerce.service.impl;

import labs.spring.spacecatsecommerce.AbstractIT;
import labs.spring.spacecatsecommerce.dto.CategoryDTO;
import labs.spring.spacecatsecommerce.repository.CategoryRepository;
import labs.spring.spacecatsecommerce.repository.entity.CategoryEntity;
import labs.spring.spacecatsecommerce.service.CategoryService;
import labs.spring.spacecatsecommerce.service.mapper.CategoryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryServiceImplTest extends AbstractIT {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @BeforeEach
    void setUp() {
        categoryRepository.deleteAll();
    }

    @Test
    @Transactional
    void testGetCategoryById() {
        // Arrange
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .name("Electronics")
                .description("Devices and gadgets")
                .build();
        categoryRepository.save(categoryEntity);

        // Act
        CategoryDTO categoryDTO = categoryService.getCategoryById(categoryEntity.getId());

        // Assert
        assertNotNull(categoryDTO);
        assertEquals("Electronics", categoryDTO.getName());
        assertEquals("Devices and gadgets", categoryDTO.getDescription());
    }

    @Test
    @Transactional
    void testGetAllCategories() {
        // Arrange
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

        // Act
        List<CategoryDTO> categories = categoryService.getAllCategories();

        // Assert
        assertNotNull(categories);
        assertEquals(2, categories.size());
    }

    @Test
    @Transactional
    void testCreateCategory() {
        // Arrange
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .name("Books")
                .description("Various kinds of books")
                .build();

        // Act
        CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);

        // Assert
        assertNotNull(createdCategory);
        assertEquals("Books", createdCategory.getName());
        assertTrue(categoryRepository.existsById(createdCategory.getId()));
    }
}
