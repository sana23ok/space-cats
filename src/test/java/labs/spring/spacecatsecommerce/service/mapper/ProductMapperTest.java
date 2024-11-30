//package labs.spring.spacecatsecommerce.service.mapper;
//
//import labs.spring.spacecatsecommerce.common.ProductType;
//import labs.spring.spacecatsecommerce.domain.Product;
//import labs.spring.spacecatsecommerce.dto.ProductDTO;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//class ProductMapperTest {
//
//    private ProductMapper productMapper;
//
//    @BeforeEach
//    void setUp() {
//        productMapper = new ProductMapperImpl();
//    }
//
//    @Test
//    void toProductDto_ShouldMapProductToProductDTO() {
//        Product product = Product.builder()
//                .id(1L)
//                .name("Sample Product")
//                .type(ProductType.COSMIC_CATNIP)
//                .price(99.99)
//                .description("A sample product description")
//                .build();
//
//        ProductDTO productDto = productMapper.toProductDto(product);
//
//        assertThat(productDto).isNotNull();
//        assertThat(productDto.getId()).isEqualTo(product.getId());
//        assertThat(productDto.getName()).isEqualTo(product.getName());
//        assertThat(productDto.getType()).isEqualTo(product.getType());
//        assertThat(productDto.getPrice()).isEqualTo(product.getPrice());
//        assertThat(productDto.getDescription()).isEqualTo(product.getDescription());
//    }
//
//    @Test
//    void toProduct_ShouldMapProductDTOToProduct() {
//        ProductDTO productDto = ProductDTO.builder()
//                .id(1L)
//                .name("Sample Product")
//                .type(ProductType.COSMIC_CATNIP)
//                .price(99.99)
//                .description("A sample product description")
//                .build();
//
//        Product product = productMapper.toProduct(productDto);
//
//        assertThat(product).isNotNull();
//        assertThat(product.getId()).isEqualTo(productDto.getId());
//        assertThat(product.getName()).isEqualTo(productDto.getName());
//        assertThat(product.getType()).isEqualTo(productDto.getType());
//        assertThat(product.getPrice()).isEqualTo(productDto.getPrice());
//        assertThat(product.getDescription()).isEqualTo(productDto.getDescription());
//    }
//
//    @Test
//    void toProduct_NullProductDTO_ShouldReturnNull() {
//        Product product = productMapper.toProduct(null);
//        assertThat(product).isNull();
//    }
//
//    @Test
//    void toProductDtoList_ShouldMapListOfProductsToListOfProductDTOs() {
//        Product product1 = Product.builder()
//                .id(1L)
//                .name("Product 1")
//                .type(ProductType.COSMIC_CATNIP)
//                .price(199.99)
//                .description("First product description")
//                .build();
//
//        Product product2 = Product.builder()
//                .id(2L)
//                .name("Product 2")
//                .type(ProductType.COSMIC_CATNIP)
//                .price(29.99)
//                .description("Second product description")
//                .build();
//
//        List<ProductDTO> productDtos = productMapper.toProductDto(List.of(product1, product2));
//
//        assertThat(productDtos).isNotNull();
//        assertThat(productDtos).hasSize(2);
//        assertThat(productDtos.get(0).getName()).isEqualTo(product1.getName());
//        assertThat(productDtos.get(1).getName()).isEqualTo(product2.getName());
//    }
//
//    @Test
//    void toProductDtoList_EmptyList_ShouldReturnEmptyList() {
//        List<ProductDTO> productDtos = productMapper.toProductDto(Collections.emptyList());
//        assertThat(productDtos).isNotNull().isEmpty();
//    }
//}
