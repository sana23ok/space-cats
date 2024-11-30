package labs.spring.spacecatsecommerce.service.impl;

import labs.spring.spacecatsecommerce.common.ProductType;
import labs.spring.spacecatsecommerce.domain.Category;
import labs.spring.spacecatsecommerce.domain.Product;
import labs.spring.spacecatsecommerce.repository.CategoryRepository;
import labs.spring.spacecatsecommerce.repository.ProductRepository;
import labs.spring.spacecatsecommerce.repository.entity.CategoryEntity;
import labs.spring.spacecatsecommerce.repository.entity.ProductEntity;
import labs.spring.spacecatsecommerce.service.ProductService;
import labs.spring.spacecatsecommerce.service.exception.PersistenceException;
import labs.spring.spacecatsecommerce.service.exception.ProductNotFoundException;
import labs.spring.spacecatsecommerce.service.mapper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public List<Product> getAllProducts() {
        try {
            Iterator<ProductEntity> productEntities = productRepository.findAll().iterator();

            if (!productEntities.hasNext()) {
                throw new ProductNotFoundException(1L);
            }

            List<Product> products = productMapper.fromProductEntities(productEntities);

            return products;
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }


    @Override
    @Transactional
    public Product getProductById(Long productId) {
        try {
            ProductEntity productEntity = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException(productId));
            return mapToDomainProduct(productEntity);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    @Transactional
    public Product createProduct(Product product) {
        try {
            CategoryEntity categoryEntity = Optional.ofNullable(product.getCategory())
                    .map(c -> categoryRepository.findById(c.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid category")))
                    .orElseGet(() -> categoryRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException("Default category not found")));

            ProductEntity productEntity = mapToEntity(product, categoryEntity);
            productRepository.save(productEntity);
            log.info("Product created: {}", product);
            return mapToDomainProduct(productEntity);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    @Transactional
    public Product updateProduct(Long productId, Product updatedProduct) {
        try {
            ProductEntity existingProductEntity = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException(productId));

            CategoryEntity categoryEntity = Optional.ofNullable(updatedProduct.getCategory())
                    .map(c -> categoryRepository.findById(c.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid category")))
                    .orElse(existingProductEntity.getCategory());

            ProductEntity productEntity = mapToEntity(updatedProduct, categoryEntity);
            productEntity.setId(existingProductEntity.getId());
            productRepository.save(productEntity);
            log.info("Product updated: {}", productEntity);
            return mapToDomainProduct(productEntity);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    @Transactional
    public void deleteProduct(Long productId) {
        try {
            ProductEntity productEntity = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException(productId));
            productRepository.delete(productEntity);
            log.info("Product deleted with id: {}", productId);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    private Product mapToDomainProduct(ProductEntity productEntity) {
        return Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .type(ProductType.valueOf(productEntity.getType().name()))
                .price(productEntity.getPrice())
                .description(productEntity.getDescription())
                .category(mapToDomainCategory(productEntity.getCategory()))
                .build();
    }

    private List<Product> mapToDomainProducts(List<ProductEntity> productEntities) {
        return productEntities.stream()
                .map(this::mapToDomainProduct)
                .toList();
    }

    private Category mapToDomainCategory(CategoryEntity categoryEntity) {
        return Category.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .description(categoryEntity.getDescription())
                .build();
    }

    private ProductEntity mapToEntity(Product product, CategoryEntity categoryEntity) {
        return ProductEntity.builder()
                .name(product.getName())
                .type(product.getType())
                .price(product.getPrice())
                .description(product.getDescription())
                .category(categoryEntity)
                .build();
    }
}
