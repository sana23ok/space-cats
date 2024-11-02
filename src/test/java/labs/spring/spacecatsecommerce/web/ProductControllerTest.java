//package labs.spring.spacecatsecommerce.web;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import labs.spring.spacecatsecommerce.dto.ProductDTO;
//import labs.spring.spacecatsecommerce.service.ProductService;
//import labs.spring.spacecatsecommerce.service.exception.ProductNotFoundException;
//import labs.spring.spacecatsecommerce.service.mapper.ProductMapper;
//import labs.spring.spacecatsecommerce.web.ProductController;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Collections;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(ProductController.class)
//class ProductControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private ProductService productService;
//
//    @Mock
//    private ProductMapper productMapper;
//
//    @InjectMocks
//    private ProductController productController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
//    }
//
//    @Test
//    void testGetAllProducts() throws Exception {
//        when(productService.getAllProducts()).thenReturn(Collections.emptyList());
//
//        mockMvc.perform(get("/products"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//
//        verify(productService, times(1)).getAllProducts();
//    }
//
//
//    @Test
//    void testDeleteProduct_Success() throws Exception {
//        mockMvc.perform(delete("/products/1"))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    void testDeleteProduct_NotFound() throws Exception {
//        doThrow(new ProductNotFoundException(100L)).when(productService).deleteProduct(100L);
//
//        mockMvc.perform(delete("/products/100"))
//                .andExpect(status().isNotFound());
//    }
//}
