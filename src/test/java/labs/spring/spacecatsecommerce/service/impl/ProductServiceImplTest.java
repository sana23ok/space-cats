package labs.spring.spacecatsecommerce.service.impl;

import labs.spring.spacecatsecommerce.common.ProductType;
import labs.spring.spacecatsecommerce.domain.Product;
import labs.spring.spacecatsecommerce.service.ProductService;
import labs.spring.spacecatsecommerce.service.exception.ProductNotFoundException;
import labs.spring.spacecatsecommerce.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl();
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = productService.getAllProducts();
        assertNotNull(products);
        assertEquals(3, products.size());
    }

    @Test
    void testGetProductById_Success() {
        Product product = productService.getProductById(1L);
        assertNotNull(product);
        assertEquals("Laser Pointer Asteroid", product.getName());
    }

    @Test
    void testGetProductById_NotFound() {
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(100L));
    }

    @Test
    void testCreateProduct() {
        Product newProduct = Product.builder()
                .id(4L)
                .name("New Space Toy")
                .type(ProductType.NEBULA_NAPPING_PODS)
                .price(29.99)
                .description("A new space toy for cats.")
                .build();

        Product createdProduct = productService.createProduct(newProduct);
        assertNotNull(createdProduct);
        assertEquals(newProduct.getName(), createdProduct.getName());
    }

    @Test
    void testUpdateProduct_Success() {
        Product updatedProduct = Product.builder()
                .id(1L)
                .name("Updated Name")
                .type(ProductType.COSMIC_CATNIP)
                .price(20.0)
                .description("Updated description")
                .build();

        Product result = productService.updateProduct(1L, updatedProduct);
        assertEquals("Updated Name", result.getName());
        assertEquals(20.0, result.getPrice());
    }

    @Test
    void testUpdateProduct_NotFound() {
        Product updatedProduct = Product.builder()
                .id(10L)
                .name("Nonexistent Product")
                .type(ProductType.COSMIC_CATNIP)
                .price(10.0)
                .description("Nonexistent description")
                .build();

        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(10L, updatedProduct));
    }

    @Test
    void testDeleteProduct_Success() {
        productService.deleteProduct(1L);
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(1L));
    }

    @Test
    void testDeleteProduct_NotFound() {
        assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(100L));
    }
}
