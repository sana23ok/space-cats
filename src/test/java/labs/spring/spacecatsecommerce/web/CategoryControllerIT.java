package labs.spring.spacecatsecommerce.web;

import labs.spring.spacecatsecommerce.dto.CategoryDTO;
import labs.spring.spacecatsecommerce.service.CategoryService;
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

@WebMvcTest(CategoryController.class)
class CategoryControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCategories_ShouldReturnListOfCategories() throws Exception {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .id(1L)
                .name("Category 1")
                .build();

        when(categoryService.getAllCategories()).thenReturn(List.of(categoryDTO));

        mockMvc.perform(get("/api/v1/category"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Category 1"));
    }

    @Test
    void getCategoryById_ShouldReturnCategory() throws Exception {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .id(1L)
                .name("Category 1")
                .build();

        when(categoryService.getCategoryById(1L)).thenReturn(categoryDTO);

        mockMvc.perform(get("/api/v1/category/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Category 1"));
    }

    @Test
    void createCategory_ShouldReturnCreatedCategory() throws Exception {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .name("Category 1")
                .build();

        CategoryDTO createdCategoryDTO = CategoryDTO.builder()
                .id(1L)
                .name("Category 1")
                .build();

        when(categoryService.createCategory(any(CategoryDTO.class))).thenReturn(createdCategoryDTO);

        mockMvc.perform(post("/api/v1/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Category 1\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Category 1"));
    }

    @Test
    void getCategoryById_ShouldReturnNotFound() throws Exception {
        when(categoryService.getCategoryById(1L)).thenReturn(null);

        mockMvc.perform(get("/api/v1/category/1"))
                .andExpect(status().isNotFound());
    }
}
