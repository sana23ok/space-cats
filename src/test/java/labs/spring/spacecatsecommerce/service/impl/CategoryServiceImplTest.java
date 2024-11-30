package labs.spring.spacecatsecommerce.service.impl;

import labs.spring.spacecatsecommerce.dto.CategoryDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private List<CategoryDTO> mockCategories;

    @BeforeEach
    void setUp() {
        mockCategories = List.of(
                CategoryDTO.builder().id(1L).name("Electronics").description("Devices and gadgets").build(),
                CategoryDTO.builder().id(2L).name("Clothing").description("Apparel and fashion items").build(),
                CategoryDTO.builder().id(3L).name("Books").description("Various kinds of books").build()
        );
    }

    @Test
    @Order(1)
    void testGetCategoryById_whenCategoryExists() {
        Long existingCategoryId = 1L;
        CategoryDTO expectedCategory = mockCategories.get(0);
        CategoryDTO category = categoryService.getCategoryById(existingCategoryId);

        assertNotNull(category, "Category should not be null");
        assertEquals(expectedCategory.getId(), category.getId(), "Category ID should match");
        assertEquals(expectedCategory.getName(), category.getName(), "Category name should match");
        assertEquals(expectedCategory.getDescription(), category.getDescription(), "Category description should match");
    }

    @Test
    @Order(2)
    void testGetCategoryById_whenCategoryDoesNotExist() {
        Long nonExistingCategoryId = 99L;
        CategoryDTO category = categoryService.getCategoryById(nonExistingCategoryId);

        assertNull(category, "Category should be null when not found");
    }

    @Test
    @Order(3)
    void testGetAllCategories() {
        List<CategoryDTO> expectedCategories = mockCategories;
        List<CategoryDTO> categories = categoryService.getAllCategories();

        assertNotNull(categories, "Categories list should not be null");
        assertEquals(expectedCategories.size(), categories.size(), "Category list size should match");
        assertTrue(categories.containsAll(expectedCategories), "Categories list should contain all expected categories");
    }
}
