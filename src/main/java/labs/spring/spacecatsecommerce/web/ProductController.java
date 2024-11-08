package labs.spring.spacecatsecommerce.web;

import labs.spring.spacecatsecommerce.dto.ProductDTO;
import labs.spring.spacecatsecommerce.domain.Product;
import labs.spring.spacecatsecommerce.service.ProductService;
import labs.spring.spacecatsecommerce.service.mapper.ProductMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/products")//веріонування
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @Operation(summary = "Retrieve all products",
            description = "Returns a list of all available products.")
    @ApiResponse(responseCode = "200",
            description = "List of products retrieved successfully")
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(productMapper.toProductDto(products));
    }

    @Operation(summary = "Retrieve a product by ID",
            description = "Returns a single product by its unique ID.")
    @ApiResponse(responseCode = "200", description = "Product retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productMapper.toProductDto(productService.getProductById(id)));
    }

    @Operation(summary = "Create a new product",
            description = "Creates a new product with the given details.")
    @ApiResponse(responseCode = "201", description = "Product created successfully")
    @RequestBody(description = "Product details for creation", required = true)
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@org.springframework.web.bind.annotation.RequestBody @Valid ProductDTO productDTO) {
        //System.out.println(productDTO);
        Product product = productMapper.toProduct(productDTO);
        //System.out.println(product);
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(productMapper.toProductDto(createdProduct));
    }

    @Operation(summary = "Update an existing product",
            description = "Updates an existing product with the specified ID.")
    @ApiResponse(responseCode = "200", description = "Product updated successfully")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @org.springframework.web.bind.annotation.RequestBody @Valid ProductDTO productDTO) {
        System.out.println(productDTO);
        Product product = productMapper.toProduct(productDTO);
        System.out.println(product);
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(productMapper.toProductDto(updatedProduct));
    }

    @Operation(summary = "Delete a product by ID",
            description = "Deletes a product with the specified ID.")
    @ApiResponse(responseCode = "204", description = "Product deleted successfully")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
