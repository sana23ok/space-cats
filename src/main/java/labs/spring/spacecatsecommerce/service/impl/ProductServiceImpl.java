package labs.spring.spacecatsecommerce.service.impl;

import labs.spring.spacecatsecommerce.common.ProductType;
import labs.spring.spacecatsecommerce.domain.Category;
import labs.spring.spacecatsecommerce.domain.Product;
import labs.spring.spacecatsecommerce.service.ProductService;
import labs.spring.spacecatsecommerce.service.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


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

        if (product.getCategory() == null) {
            Category defaultCategory = Category.builder()
                    .id(1L)
                    .name("Default Category")
                    .description("Default category description")
                    .build();
            product = product.toBuilder()
                    .category(defaultCategory)
                    .build();
        }

        products.add(product);
        log.info("Product created: {}", product);
        return product;
    }

    @Override
    public Product updateProduct(Long productId, Product updatedProduct) {
        Product existingProduct = getProductById(productId);

        Product product = Product.builder()
                .id(existingProduct.getId())
                .name(updatedProduct.getName())
                .type(updatedProduct.getType())
                .price(updatedProduct.getPrice())
                .description(updatedProduct.getDescription())
                .category(updatedProduct.getCategory() != null ? updatedProduct.getCategory() : existingProduct.getCategory())  // Keep the old category if none provided
                .build();

        int index = products.indexOf(existingProduct);

        if (index < 0 ) {
            throw new NoSuchElementException("Product not found: " + productId);
        }

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
        Category electronics = Category.builder()
                .id(1L)
                .name("Electronics")
                .description("Devices and gadgets")
                .build();

        Category toys = Category.builder()
                .id(2L)
                .name("Toys")
                .description("Fun items for pets")
                .build();

        return new ArrayList<>(List.of(
                Product.builder()
                        .id(1L)
                        .name("Laser Pointer Asteroid")
                        .type(ProductType.COSMIC_CATNIP)
                        .price(15.99)
                        .description("A high-quality laser pointer.")
                        .category(electronics)
                        .build(),
                Product.builder()
                        .id(2L)
                        .name("Catnip Toy Meteor")
                        .type(ProductType.NEBULA_NAPPING_PODS)
                        .price(9.99)
                        .description("A fun toy filled with catnip.")
                        .category(toys)
                        .build(),
                Product.builder()
                        .id(3L)
                        .name("Galaxy Feline Feeder")
                        .type(ProductType.PLASMA_PAW_WARMERS)
                        .price(39.99)
                        .description("An automatic feeder for cats.")
                        .category(electronics)
                        .build()
        ));
    }
}
