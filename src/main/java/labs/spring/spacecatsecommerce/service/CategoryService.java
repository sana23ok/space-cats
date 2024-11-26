package labs.spring.spacecatsecommerce.service;

import labs.spring.spacecatsecommerce.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO getCategoryById(Long id);

    List<CategoryDTO> getAllCategories();
}
