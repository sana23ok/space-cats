//package labs.spring.spacecatsecommerce.web;
//
//import labs.spring.spacecatsecommerce.dto.ProductDTO;
//import labs.spring.spacecatsecommerce.domain.Product; // Ensure this import is present
//import labs.spring.spacecatsecommerce.service.ProductService;
//import labs.spring.spacecatsecommerce.service.mapper.ProductMapper;
//import jakarta.validation.Valid;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@Validated
//@RequestMapping("/products")
//public class ProductController {
//
//    private final ProductService productService;
//    private final ProductMapper productMapper;
//
//    public ProductController(ProductService productService, ProductMapper productMapper) {
//        this.productService = productService;
//        this.productMapper = productMapper;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<ProductDTO>> getAllProducts() {
//        List<Product> products = productService.getAllProducts();
//        return ResponseEntity.ok(productMapper.toProductDto(products)); // This should now work
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
//        return ResponseEntity.ok(productMapper.toProductDto(productService.getProductById(id)));
//    }
//
//    @PostMapping
//    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO productDTO) {
//        Product product = productMapper.toProduct(productDTO); // This should now work
//        Product createdProduct = productService.createProduct(product);
//        return ResponseEntity.ok(productMapper.toProductDto(createdProduct));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO) {
//        Product product = productMapper.toProduct(productDTO); // This should now work
//        Product updatedProduct = productService.updateProduct(id, product);
//        return ResponseEntity.ok(productMapper.toProductDto(updatedProduct));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
//        productService.deleteProduct(id);
//        return ResponseEntity.noContent().build();
//    }
//}
