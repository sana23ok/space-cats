package labs.spring.spacecatsecommerce.service.impl;

import labs.spring.spacecatsecommerce.common.ProductType;
import labs.spring.spacecatsecommerce.domain.Product;
import labs.spring.spacecatsecommerce.service.ProductService;
import labs.spring.spacecatsecommerce.service.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final List<Product> products = buildAllProductsMock();

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Product getProductById(Long productId) {
        return products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> {
                    log.info("Product with id {} not found", productId);
                    return new ProductNotFoundException(productId);
                });
    }

    @Override
    public Product createProduct(Product product) {
        products.add(product);
        log.info("Product created: {}", product);
        return product;
    }

    @Override
    public Product updateProduct(Long productId, Product updatedProduct) {
        Product product = getProductById(productId);
        // Assuming you want to update all fields of the product
        product = Product.builder()
                .id(product.getId()) // Keep the same ID
                .name(updatedProduct.getName())
                .type(updatedProduct.getType())
                .price(updatedProduct.getPrice())
                .description(updatedProduct.getDescription())
                .build();

        int index = products.indexOf(product);
        products.set(index, product);
        log.info("Product updated: {}", product);
        return product;
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = getProductById(productId);
        products.remove(product);
        log.info("Product deleted with id: {}", productId);
    }

    private List<Product> buildAllProductsMock() {
        return new ArrayList<>(List.of(
                Product.builder()
                        .id(1L)
                        .name("Laser Pointer")
                        .type(ProductType.COSMIC_CATNIP)
                        .price(15.99)
                        .description("A high-quality laser pointer.")
                        .build(),
                Product.builder()
                        .id(2L)
                        .name("Catnip Toy")
                        .type(ProductType.NEBULA_NAPPING_PODS)
                        .price(9.99)
                        .description("A fun toy filled with catnip.")
                        .build(),
                Product.builder()
                        .id(3L)
                        .name("Feline Feeder")
                        .type(ProductType.PLASMA_PAW_WARMERS)
                        .price(39.99)
                        .description("An automatic feeder for cats.")
                        .build()
        ));
    }
}