package labs.spring.spacecatsecommerce.service.impl;

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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("ProductService Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @SpyBean
    private ProductRepository productRepository;

    @SpyBean
    private CategoryRepository categoryRepository;

    private static Long newProductId;
    private static Long existingProductId;

    @BeforeEach
    void setUp() {
        // Створюємо категорії за допомогою білдерів
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

        // Зберігаємо категорії в репозиторії
        categoryRepository.save(electronics);
        categoryRepository.save(toys);

        // Створюємо продукти за допомогою білдерів
        ProductEntity product1 = ProductEntity.builder()
                .id(1L)
                .name("Laser Pointer Asteroid")
                .description("A high-quality laser pointer.")
                .price(15.99)
                .category(electronics) // Використовуємо вже збережену категорію
                .build();
        ProductEntity product2 = ProductEntity.builder()
                .id(2L)
                .name("Catnip Toy Meteor")
                .description("A fun toy filled with catnip.")
                .price(9.99)
                .category(toys) // Використовуємо вже збережену категорію
                .build();

        // Зберігаємо продукти в репозиторії ProductRepository
        productRepository.save(product1);
        productRepository.save(product2);

        existingProductId = product1.getId();
        System.out.println(existingProductId);
        // Ідентифікатор існуючого продукту
        newProductId = 3L; // новий ID для тесту створення
    }

    @Test
    @Order(1)
    void shouldReturnAllProducts() {
//        productService.deleteProduct(existingProductId);
        List<Product> products = productService.getAllProducts();
        System.out.println(products);
        assertNotNull(products);
        assertEquals(3, products.size());
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

        // Створення нового продукту за допомогою білдера
        Product newProduct = Product.builder()
                .id(newProductId)
                .name("Space Cat Toy")
                .type(ProductType.PLASMA_PAW_WARMERS)
                .price(19.99)
                .description("A cool toy for space cats.")
                .category(Category.builder()  // Використовуємо білдер для категорії
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
        // Видалення продукту
        productService.deleteProduct(existingProductId);
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(existingProductId));
    }

    @Test
    @Order(6)
    void shouldThrowProductNotFoundException() {
        // Перевірка винятку для неіснуючого продукту
        Long randomId = 999L;
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(randomId));
    }

//    @Test
//    @Order(7)
//    void shouldThrowProductsNotFoundException() {
//        // Перевірка винятку для порожнього списку продуктів
//        productRepository.deleteAll();
//        assertThrows(ProductsNotFoundException.class, () -> productService.getAllProducts());
//    }

    @Order(8)
    void shouldThrowPersistenceException() {
        when(productRepository.findById(anyLong())).thenThrow(JDBCConnectionException.class);
        assertThrows(PersistenceException.class, () -> productService.getProductById(existingProductId));
    }



    @AfterEach
    void cleanUp() {
        productRepository.deleteAll();
//        categoryRepository.deleteAll();
    }
}
