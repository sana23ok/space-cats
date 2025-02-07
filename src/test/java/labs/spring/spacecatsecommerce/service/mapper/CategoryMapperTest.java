package labs.spring.spacecatsecommerce.service.mapper;

import labs.spring.spacecatsecommerce.dto.CategoryDTO;
import labs.spring.spacecatsecommerce.repository.entity.CategoryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryMapperTest {

    private CategoryMapper categoryMapper;

    @BeforeEach
    void setUp() {
        categoryMapper = new CategoryMapperImpl();
    }

    @Test
    void toCategoryDto_ShouldMapCategoryEntityToCategoryDTO() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setName("Electronics");
        categoryEntity.setDescription("All kinds of electronic items");

        CategoryDTO categoryDTO = categoryMapper.toCategoryDto(categoryEntity);

        assertThat(categoryDTO).isNotNull();
        assertThat(categoryDTO.getId()).isEqualTo(categoryEntity.getId());
        assertThat(categoryDTO.getName()).isEqualTo(categoryEntity.getName());
        assertThat(categoryDTO.getDescription()).isEqualTo(categoryEntity.getDescription());
    }

    @Test
    void toCategoryDto_NullCategoryEntity_ShouldReturnNull() {
        CategoryDTO categoryDTO = categoryMapper.toCategoryDto(null);
        assertThat(categoryDTO).isNull();
    }

    @Test
    void toCategoryEntity_ShouldMapCategoryDTOToCategoryEntity(){
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .id(2L)
                .name("Furniture")
                .description("Home and office furniture")
                .build();

        CategoryEntity categoryEntity = categoryMapper.toCategoryEntity(categoryDTO);

        assertThat(categoryEntity).isNotNull();
        assertThat(categoryEntity.getId()).isEqualTo(categoryDTO.getId());
        assertThat(categoryEntity.getName()).isEqualTo(categoryDTO.getName());
        assertThat(categoryEntity.getDescription()).isEqualTo(categoryDTO.getDescription());
    }


    @Test
    void toCategoryEntity_NullCategoryDTO_ShouldReturnNull() {
        CategoryEntity categoryEntity = categoryMapper.toCategoryEntity(null);
        assertThat(categoryEntity).isNull();
    }
}

