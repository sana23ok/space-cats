//package labs.spring.spacecatsecommerce.web;
//
//import labs.spring.spacecatsecommerce.dto.CategoryDTO;
//import labs.spring.spacecatsecommerce.service.CategoryService;
//import labs.spring.spacecatsecommerce.service.mapper.CategoryMapper;
//import jakarta.validation.Valid;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@Validated
//@RequestMapping("/api/v1/categories")
//public class CategoryController {
//
//    private final CategoryService categoryService;
//    private final CategoryMapper categoryMapper;
//
//    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
//        this.categoryService = categoryService;
//        this.categoryMapper = categoryMapper;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
//        return ResponseEntity.ok(categoryMapper.toCategoryDTO(categoryService.getCategoryById(id)));
//    }
//
//    @PostMapping
//    public ResponseEntity<CategoryDTO> createCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
//        return ResponseEntity.ok(categoryMapper.toCategoryDTO(categoryService.createCategory(categoryDTO)));
//    }
//}
