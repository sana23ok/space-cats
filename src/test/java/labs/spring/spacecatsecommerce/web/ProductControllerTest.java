package labs.spring.spacecatsecommerce.web;

import labs.spring.spacecatsecommerce.dto.ProductDTO;
import labs.spring.spacecatsecommerce.domain.Product;
import labs.spring.spacecatsecommerce.service.ProductService;
import labs.spring.spacecatsecommerce.service.mapper.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private ProductMapper productMapper;

    private Product product;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Using builder pattern to create instances
        product = Product.builder()
                .id(1L)
                .name("Test Product")
                .build();

        productDTO = ProductDTO.builder()
                .id(1L)
                .name("Test Product")
                .build();
    }

//    @Test
//    void getAllProducts_shouldReturnProductList() {
//        // Arrange
//        when(productService.getAllProducts()).thenReturn(Arrays.asList(product));
//        when(productMapper.toProductDto(any(Product.class))).thenReturn(productDTO);
//
//        // Act
//        ResponseEntity<List<ProductDTO>> response = productController.getAllProducts();
//
//        // Assert
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals(1, response.getBody().size());
//        assertEquals("Test Product", response.getBody().get(0).getName());
//        verify(productService).getAllProducts();
//        verify(productMapper).toProductDto(product);
//    }


    @Test
    void getProductById_existingId_shouldReturnProduct() {
        // Arrange
        when(productService.getProductById(1L)).thenReturn(product);
        when(productMapper.toProductDto(product)).thenReturn(productDTO);

        // Act
        ResponseEntity<ProductDTO> response = productController.getProductById(1L);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test Product", response.getBody().getName());
        verify(productService).getProductById(1L);
    }

    @Test
    void getProductById_nonExistingId_shouldReturn404() {
        // Arrange
        when(productService.getProductById(999L)).thenThrow(new RuntimeException("Product not found"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            productController.getProductById(999L);
        });

        assertEquals("Product not found", exception.getMessage());
        verify(productService).getProductById(999L);
    }

    @Test
    void createProduct_shouldReturnCreatedProduct() {
        // Arrange
        when(productMapper.toProduct(productDTO)).thenReturn(product);
        when(productService.createProduct(product)).thenReturn(product);
        when(productMapper.toProductDto(product)).thenReturn(productDTO);

        // Act
        ResponseEntity<ProductDTO> response = productController.createProduct(productDTO);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test Product", response.getBody().getName());
        verify(productMapper).toProduct(productDTO);
        verify(productService).createProduct(product);
    }

    @Test
    void updateProduct_existingId_shouldReturnUpdatedProduct() {
        // Arrange
        when(productMapper.toProduct(productDTO)).thenReturn(product);
        when(productService.updateProduct(1L, product)).thenReturn(product);
        when(productMapper.toProductDto(product)).thenReturn(productDTO);

        // Act
        ResponseEntity<ProductDTO> response = productController.updateProduct(1L, productDTO);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test Product", response.getBody().getName());
        verify(productService).updateProduct(1L, product);
    }

    @Test
    void deleteProduct_existingId_shouldReturn204() {
        // Arrange
        doNothing().when(productService).deleteProduct(1L);

        // Act
        ResponseEntity<Void> response = productController.deleteProduct(1L);

        // Assert
        assertEquals(204, response.getStatusCodeValue());
        verify(productService).deleteProduct(1L);
    }

    @Test
    void deleteProduct_nonExistingId_shouldReturn404() {
        // Arrange
        doThrow(new RuntimeException("Product not found")).when(productService).deleteProduct(999L);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productController.deleteProduct(999L);
        });

        assertEquals("Product not found", exception.getMessage());
        verify(productService).deleteProduct(999L);
    }

}
