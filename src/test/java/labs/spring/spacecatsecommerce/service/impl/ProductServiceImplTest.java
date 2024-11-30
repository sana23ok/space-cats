package labs.spring.spacecatsecommerce.service.impl;

import labs.spring.spacecatsecommerce.AbstractIT;
import labs.spring.spacecatsecommerce.service.exception.ProductNotFoundException;
import labs.spring.spacecatsecommerce.common.ProductType;
import labs.spring.spacecatsecommerce.domain.Category;
import labs.spring.spacecatsecommerce.domain.Product;
import labs.spring.spacecatsecommerce.repository.CategoryRepository;
import labs.spring.spacecatsecommerce.repository.ProductRepository;
import labs.spring.spacecatsecommerce.repository.entity.CategoryEntity;
import labs.spring.spacecatsecommerce.repository.entity.ProductEntity;
import labs.spring.spacecatsecommerce.service.ProductService;
import labs.spring.spacecatsecommerce.service.exception.PersistenceException;
import labs.spring.spacecatsecommerce.service.exception.ProductNotFoundException;
import org.hibernate.exception.JDBCConnectionException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.List;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("ProductService Tests with Testcontainers")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceImplTest extends AbstractIT {

    @Autowired
    private ProductService productService;

    @SpyBean
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private static Long newProductId;
    private static Long existingProductId;

    @BeforeEach
    void setUp() {
        reset(productRepository);

        // Create categories using builder
        CategoryEntity electronics = CategoryEntity.builder()
                .id(1L)
                .name("Electronics")
                .description("Devices and gadgets")
                .build();
        CategoryEntity toys = CategoryEntity.builder()
                .id(2L)
                .name("Toys")
                .description("Fun items for pets")
                .build();

        // Save categories in the repository
        categoryRepository.save(electronics);
        categoryRepository.save(toys);

        // Convert Iterable to List and log categories to verify they are saved
        List<CategoryEntity> categories = ((List<CategoryEntity>) categoryRepository.findAll())
                .stream()
                .collect(Collectors.toList());
        categories.forEach(category -> System.out.println("Saved category: " + category.getName()));

        // Verify that categories are saved
        Optional<CategoryEntity> savedElectronics = categoryRepository.findById(1L);
        Optional<CategoryEntity> savedToys = categoryRepository.findById(2L);

        assertTrue(savedElectronics.isPresent(), "Electronics category should be present");
        assertTrue(savedToys.isPresent(), "Toys category should be present");

        // Create products using builder
        ProductEntity product1 = ProductEntity.builder()
                .id(1L)
                .name("Laser Pointer Asteroid")
                .description("A high-quality laser pointer.")
                .price(15.99)
                .category(electronics)
                .build();
        ProductEntity product2 = ProductEntity.builder()
                .id(2L)
                .name("Catnip Toy Meteor")
                .description("A fun toy filled with catnip.")
                .price(9.99)
                .category(toys)
                .build();

        // Save products in the repository
        productRepository.save(product1);
        productRepository.save(product2);

        // Convert Iterable to List and log products to verify they are saved
        List<ProductEntity> products = ((List<ProductEntity>) productRepository.findAll())
                .stream()
                .collect(Collectors.toList());
        products.forEach(product -> System.out.println("Saved product: " + product.getName()));

        // Assign IDs for existing and new products
        existingProductId = product1.getId();
        newProductId = 3L;
    }




    @Test
    @Order(1)
    void shouldReturnAllProducts() {
        List<Product> products = productService.getAllProducts();
        assertNotNull(products);
        assertEquals(2, products.size());
    }

    @Test
    @Order(2)
    void shouldReturnProductById() {
        Product product = productService.getProductById(existingProductId);
        assertNotNull(product);
        assertEquals("Laser Pointer Asteroid", product.getName());
    }

    @Test
    @Order(3)
    void shouldCreateProduct() {
        Product newProduct = Product.builder()
                .id(newProductId)
                .name("Space Cat Toy")
                .type(ProductType.PLASMA_PAW_WARMERS)
                .price(19.99)
                .description("A cool toy for space cats.")
                .category(Category.builder()
                        .id(1L)
                        .name("Electronics")
                        .description("Devices and gadgets")
                        .build())
                .build();

        Product createdProduct = productService.createProduct(newProduct);
        assertNotNull(createdProduct);
        assertEquals("Space Cat Toy", createdProduct.getName());
    }

    @Test
    @Order(4)
    void shouldUpdateProduct() {
        Product updatedProduct = Product.builder()
                .id(existingProductId)
                .name("Updated Laser Pointer")
                .type(ProductType.COSMIC_CATNIP)
                .price(18.99)
                .description("Updated description")
                .category(Category.builder()
                        .name("Electronics")
                        .description("Devices and gadgets")
                        .build())
                .build();

        Product product = productService.updateProduct(existingProductId, updatedProduct);
        assertNotNull(product);
        assertEquals("Updated Laser Pointer", product.getName());
    }

    @Test
    @Order(5)
    void shouldDeleteProduct() {
        productService.deleteProduct(existingProductId);
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(existingProductId));
    }

    @Test
    @Order(6)
    void shouldThrowProductNotFoundException() {
        Long randomId = 999L;
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(randomId));
    }

//    @Test
//    @Order(7)
//    void shouldThrowProductsNotFoundException() {
//        productRepository.deleteAll();
//        assertThrows(ProductsNotFoundException.class, () -> productService.getAllProducts());
//    }

    @Test
    @Order(8)
    void shouldThrowPersistenceException() {
        when(productRepository.findById(anyLong())).thenThrow(JDBCConnectionException.class);
        assertThrows(PersistenceException.class, () -> productService.getProductById(existingProductId));
    }

    @AfterEach
    void cleanUp() {
        productRepository.deleteAll();
        categoryRepository.deleteAll();
    }
}
