package labs.spring.spacecatsecommerce.web;

import labs.spring.spacecatsecommerce.common.ProductType;
import labs.spring.spacecatsecommerce.domain.Category;
import labs.spring.spacecatsecommerce.dto.CategoryDTO;
import labs.spring.spacecatsecommerce.dto.ProductDTO;
import labs.spring.spacecatsecommerce.domain.Product;
import labs.spring.spacecatsecommerce.service.ProductService;
import labs.spring.spacecatsecommerce.service.mapper.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductMapper productMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts_ShouldReturnListOfProducts() throws Exception {
        Product product = Product.builder()
                .id(1L)
                .name("Product 1")
                .price(100.0)
                .build();

        ProductDTO productDTO = ProductDTO.builder()
                .id(1L)
                .name("Product 1")
                .price(100.0)
                .build();

        when(productService.getAllProducts()).thenReturn(List.of(product));
        when(productMapper.domainToDto(product)).thenReturn(productDTO);

        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Product 1"));
    }

    @Test
    void getProductById_ShouldReturnProduct() throws Exception {
        Product product = Product.builder()
                .id(1L)
                .name("Product 1")
                .price(100.0)
                .build();

        ProductDTO productDTO = ProductDTO.builder()
                .id(1L)
                .name("Product 1")
                .price(100.0)
                .build();

        when(productService.getProductById(1L)).thenReturn(product);
        when(productMapper.domainToDto(product)).thenReturn(productDTO);

        mockMvc.perform(get("/api/v1/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Product 1"));
    }

    @Test
    void createProduct_ShouldReturnCreatedProduct() throws Exception {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .id(1L)
                .name("Category 1")
                .build();

        ProductDTO productDTO = ProductDTO.builder()
                .name("Star Product")
                .type(ProductType.COSMIC_CATNIP)
                .category(categoryDTO)
                .price(100.0)
                .build();

        Product product = Product.builder()
                .name("Star Product")
                .type(ProductType.COSMIC_CATNIP)
                .price(100.0)
                .build();

        Product createdProduct = Product.builder()
                .id(1L)
                .name("Star Product")
                .type(ProductType.COSMIC_CATNIP)
                .price(100.0)
                .build();

        ProductDTO createdProductDTO = ProductDTO.builder()
                .id(1L)
                .name("Star Product")
                .type(ProductType.COSMIC_CATNIP)
                .category(categoryDTO)
                .price(100.0)
                .build();

        when(productMapper.dtoToDomain(productDTO)).thenReturn(product);
        when(productService.createProduct(product)).thenReturn(createdProduct);
        when(productMapper.domainToDto(createdProduct)).thenReturn(createdProductDTO);

        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Star Product\",\"type\":\"COSMIC_CATNIP\",\"category\":{\"id\":1,\"name\":\"Category 1\"},\"price\":100.0}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Star Product"))
                .andExpect(jsonPath("$.type").value("COSMIC_CATNIP"))
                .andExpect(jsonPath("$.category.id").value(1L))
                .andExpect(jsonPath("$.category.name").value("Category 1"));
    }


    @Test
    void deleteProduct_ShouldReturnNoContent() throws Exception {
        doNothing().when(productService).deleteProduct(1L);

        mockMvc.perform(delete("/api/v1/products/1"))
                .andExpect(status().isNoContent());
    }
}
