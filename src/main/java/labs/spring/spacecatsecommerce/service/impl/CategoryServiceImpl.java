package labs.spring.spacecatsecommerce.service.impl;

import labs.spring.spacecatsecommerce.dto.CategoryDTO;
import labs.spring.spacecatsecommerce.service.CategoryService;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final List<CategoryDTO> mockCategories = Arrays.asList(
            CategoryDTO.builder().id(1L).name("Electronics").description("Devices and gadgets").build(),
            CategoryDTO.builder().id(2L).name("Clothing").description("Apparel and fashion items").build(),
            CategoryDTO.builder().id(3L).name("Books").description("Various kinds of books").build()
    );

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Optional<CategoryDTO> category = mockCategories.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

        return category.orElse(null);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return mockCategories;
    }
}
