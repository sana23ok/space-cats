package labs.spring.spacecatsecommerce.service.impl;

import labs.spring.spacecatsecommerce.AbstractIT;
import labs.spring.spacecatsecommerce.common.ProductType;
import labs.spring.spacecatsecommerce.domain.Category;
import labs.spring.spacecatsecommerce.domain.Product;
import labs.spring.spacecatsecommerce.dto.CategoryDTO;
import labs.spring.spacecatsecommerce.repository.CategoryRepository;
import labs.spring.spacecatsecommerce.repository.ProductRepository;
import labs.spring.spacecatsecommerce.repository.entity.CategoryEntity;
import labs.spring.spacecatsecommerce.repository.entity.ProductEntity;
import labs.spring.spacecatsecommerce.service.ProductService;
import labs.spring.spacecatsecommerce.service.exception.ProductNotFoundException;
import labs.spring.spacecatsecommerce.service.mapper.ProductMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import labs.spring.spacecatsecommerce.dto.ProductDTO;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Testcontainers
@DisplayName("ProductService Tests with Testcontainers")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceImplIT extends AbstractIT {

    @Autowired
    private ProductService productService;

    @SpyBean
    @Autowired
    private ProductRepository productRepository;

    @SpyBean
    @Autowired
    private CategoryRepository categoryRepository;

    @SpyBean
    @Autowired
    private ProductMapper productMapper;

    private static Long newProductId;
    private static String newProductName = "Laser Pointer Asteroid";
    private static String updatedProductName = "Updated Laser Pointer";
    private static Long newCategoryId;

    @AfterEach
    void cleanUp() {
        productRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @BeforeEach
    void setUp() {
        reset(productRepository);
        reset(categoryRepository);

        CategoryEntity category = createCategoryEntity("Electronics", "Electronics category");
        newCategoryId = category.getId();

        ProductEntity product = createProductEntity(category, "Product A", "A high-quality laser pointer.", 15.99, ProductType.PLASMA_PAW_WARMERS);
        newProductId = product.getId();
    }

    // Helper method to create a CategoryEntity
    private CategoryEntity createCategoryEntity(String name, String description) {
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .name(name)
                .description(description)
                .build();
        return categoryRepository.save(categoryEntity);
    }

    // Helper method to create a ProductEntity
    private ProductEntity createProductEntity(CategoryEntity category, String name, String description, double price, ProductType type) {
        ProductEntity productEntity = ProductEntity.builder()
                .name(name)
                .description(description)
                .price(price)
                .type(type)
                .category(category)
                .build();
        return productRepository.save(productEntity);
    }

    // Helper method to create CategoryDTO
    private CategoryDTO createCategoryDTO(CategoryEntity categoryEntity) {
        return CategoryDTO.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .description(categoryEntity.getDescription())
                .build();
    }

    @Test
    @Order(1)
    void testGetAllProducts() {
        List<Product> productList = productService.getAllProducts();
        assertNotNull(productList);
        assertFalse(productList.isEmpty());
    }

    @Test
    @Order(2)
    void testGetProductById() {
        Product product = productService.getProductById(newProductId);
        assertNotNull(product);
        assertEquals(newProductId, product.getId());
        assertEquals("Product A", product.getName());
        assertEquals(ProductType.PLASMA_PAW_WARMERS, product.getType());
    }

    @Test
    @Order(3)
    void testCreateProduct() {
        CategoryEntity categoryEntity = createCategoryEntity("Electronics", "Electronics category");
        CategoryDTO categoryDTO = createCategoryDTO(categoryEntity);

        ProductDTO newProductDto = ProductDTO.builder()
                .name(newProductName)
                .description("A high-quality laser pointer.")
                .price(15.99)
                .type(ProductType.PLASMA_PAW_WARMERS)
                .category(categoryDTO)
                .build();

        Product newProduct = productMapper.dtoToDomain(newProductDto);
        Product createdProduct = productService.createProduct(newProduct);

        assertNotNull(createdProduct);
        assertEquals(newProductName, createdProduct.getName());
        assertEquals(ProductType.PLASMA_PAW_WARMERS, createdProduct.getType());

        ProductEntity savedProductEntity = productRepository.findById(createdProduct.getId())
                .orElseThrow(() -> new RuntimeException("Product not found in database"));
        assertEquals(newProductName, savedProductEntity.getName());
        assertEquals(ProductType.PLASMA_PAW_WARMERS, savedProductEntity.getType());
    }

    @Test
    @Order(4)
    void testUpdateProduct() {
        Category category = Category.builder()
                .id(newCategoryId)
                .name("Electronics")
                .description("Electronics category")
                .build();

        Product updatedProduct = Product.builder()
                .id(newProductId)
                .name(updatedProductName)
                .description("Updated description for the laser pointer.")
                .price(19.99)
                .type(ProductType.PLASMA_PAW_WARMERS)
                .category(category)
                .build();

        Product updated = productService.updateProduct(newProductId, updatedProduct);

        assertNotNull(updated);
        assertEquals(updatedProductName, updated.getName());
        assertEquals(ProductType.PLASMA_PAW_WARMERS, updated.getType());
        assertEquals(19.99, updated.getPrice());
    }

    @Test
    @Order(5)
    void testProductNotFoundExceptionWhenGettingProductById() {
        Long invalidId = -1L;
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> productService.getProductById(invalidId));
        assertEquals("Product not found with ID: " + invalidId, exception.getMessage());
    }

    @Test
    @Order(6)
    void testDeleteProduct() {
        productService.deleteProduct(newProductId);
        assertFalse(productRepository.existsById(newProductId));
    }

    @Test
    @Order(7)
    void testProductNotFoundExceptionWhenDeletingNonExistentProduct() {
        Long invalidId = -1L;
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(invalidId));
        assertEquals("Product not found with ID: " + invalidId, exception.getMessage());
    }
}
