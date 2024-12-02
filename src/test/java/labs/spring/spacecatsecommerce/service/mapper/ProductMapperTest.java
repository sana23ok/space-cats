package labs.spring.spacecatsecommerce.service.mapper;

import labs.spring.spacecatsecommerce.common.ProductType;
import labs.spring.spacecatsecommerce.domain.Product;
import labs.spring.spacecatsecommerce.domain.Category;
import labs.spring.spacecatsecommerce.dto.ProductDTO;
import labs.spring.spacecatsecommerce.dto.CategoryDTO;
import labs.spring.spacecatsecommerce.repository.entity.CategoryEntity;
import labs.spring.spacecatsecommerce.repository.entity.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    private ProductMapper productMapper;

    @BeforeEach
    void setUp() {
        productMapper = Mappers.getMapper(ProductMapper.class);
    }

    @Test
    void testEntityToDomain() {
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .id(1L)
                .name("Category 1")
                .build();

        ProductEntity productEntity = ProductEntity.builder()
                .id(1L)
                .name("Product 1")
                .type(ProductType.COSMIC_CATNIP)
                .price(100.0)
                .description("Description 1")
                .category(categoryEntity)
                .build();

        Product product = productMapper.entityToDomain(productEntity);

        assertNotNull(product);
        assertEquals(1L, product.getId());
        assertEquals("Product 1", product.getName());
        assertEquals(ProductType.COSMIC_CATNIP, product.getType());
        assertEquals(100.0, product.getPrice());
        assertEquals("Description 1", product.getDescription());
        assertNotNull(product.getCategory());
        assertEquals(1L, product.getCategory().getId());
        assertEquals("Category 1", product.getCategory().getName());
    }

    @Test
    void testDtoToDomain() {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .id(1L)
                .name("Category 1")
                .build();

        ProductDTO productDTO = ProductDTO.builder()
                .id(1L)
                .name("Product 1")
                .type(ProductType.COSMIC_CATNIP)
                .price(100.0)
                .description("Description 1")
                .category(categoryDTO)
                .build();

        Product product = productMapper.dtoToDomain(productDTO);

        assertNotNull(product);
        assertEquals(1L, product.getId());
        assertEquals("Product 1", product.getName());
        assertEquals(ProductType.COSMIC_CATNIP, product.getType());
        assertEquals(100.0, product.getPrice());
        assertEquals("Description 1", product.getDescription());
        assertNotNull(product.getCategory());
        assertEquals(1L, product.getCategory().getId());
        assertEquals("Category 1", product.getCategory().getName());
    }

    @Test
    void testDomainToDto() {
        Category category = Category.builder()
                .id(1L)
                .name("Category 1")
                .build();

        Product product = Product.builder()
                .id(1L)
                .name("Product 1")
                .type(ProductType.COSMIC_CATNIP)
                .price(100.0)
                .description("Description 1")
                .category(category)
                .build();

        ProductDTO productDTO = productMapper.domainToDto(product);

        assertNotNull(productDTO);
        assertEquals(1L, productDTO.getId());
        assertEquals("Product 1", productDTO.getName());
        assertEquals(ProductType.COSMIC_CATNIP, productDTO.getType());
        assertEquals(100.0, productDTO.getPrice());
        assertEquals("Description 1", productDTO.getDescription());
        assertNotNull(productDTO.getCategory());
        assertEquals(1L, productDTO.getCategory().getId());
        assertEquals("Category 1", productDTO.getCategory().getName());
    }

    @Test
    void testDomainToEntity() {
        Category category = Category.builder()
                .id(1L)
                .name("Category 1")
                .build();

        Product product = Product.builder()
                .id(1L)
                .name("Product 1")
                .type(ProductType.COSMIC_CATNIP)
                .price(100.0)
                .description("Description 1")
                .category(category)
                .build();

        ProductEntity productEntity = productMapper.domainToEntity(product);

        assertNotNull(productEntity);
        assertEquals(1L, productEntity.getId());
        assertEquals("Product 1", productEntity.getName());
        assertEquals(ProductType.COSMIC_CATNIP, productEntity.getType());
        assertEquals(100.0, productEntity.getPrice());
        assertEquals("Description 1", productEntity.getDescription());
        assertNotNull(productEntity.getCategory());
        assertEquals(1L, productEntity.getCategory().getId());
        assertEquals("Category 1", productEntity.getCategory().getName());
    }

    @Test void testDomainToEntityWithCategory()
    { CategoryEntity categoryEntity = CategoryEntity.builder()
            .id(1L) .name("Category 1")
            .build();

        Category category = Category.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .build();

        Product product = Product.builder()
                .id(1L) .name("Product 1")
                .type(ProductType.COSMIC_CATNIP)
                .price(100.0)
                .description("Description 1")
                .category(category)
                .build();

        ProductEntity productEntity = productMapper.domainToEntityWithCategory(product, categoryEntity);
        assertNotNull(productEntity); assertEquals(1L, productEntity.getId());
        assertEquals("Product 1", productEntity.getName());
        assertEquals(ProductType.COSMIC_CATNIP, productEntity.getType());
        assertEquals(100.0, productEntity.getPrice());
        assertEquals("Description 1", productEntity.getDescription());
        assertNotNull(productEntity.getCategory());
        assertEquals(1L, productEntity.getCategory().getId());
        assertEquals("Category 1", productEntity.getCategory().getName());
    }
}
