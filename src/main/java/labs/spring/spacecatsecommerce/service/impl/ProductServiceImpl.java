package labs.spring.spacecatsecommerce.service.impl;

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
import java.util.List;

@Service
@Slf4j
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
            List<ProductEntity> entities = productRepository.findAll();
            return entities.stream().map(productMapper::entityToDomain).toList();
        } catch (Exception e) {
            log.error("Error fetching products", e);
            throw new PersistenceException(e);
        }
    }

    @Override
    @Transactional
    public Product getProductById(Long productId) {
        try {
            ProductEntity entity = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException(productId));
            return productMapper.entityToDomain(entity);
        } catch (ProductNotFoundException e) {
            log.error("Product not found with ID {}", productId, e);
            throw e;
        } catch (Exception e) {
            log.error("Error fetching product with ID {}", productId, e);
            throw new PersistenceException(e);
        }
    }

    @Override
    @Transactional
    public Product createProduct(Product product) {
        try {
            CategoryEntity category = categoryRepository.findById(product.getCategory().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
            ProductEntity entity = productMapper.domainToEntityWithCategory(product, category);
            ProductEntity saved = productRepository.save(entity);
            return productMapper.entityToDomain(saved);
        } catch (Exception e) {
            log.error("Error creating product", e);
            throw new PersistenceException(e);
        }
    }

    @Override
    @Transactional
    public Product updateProduct(Long id, Product product) {
        try {
            ProductEntity existing = productRepository.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException(id));
            CategoryEntity category = categoryRepository.findById(product.getCategory().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));

            // Ensure the category entity is managed by the persistence context
            category = categoryRepository.findById(category.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));

            ProductEntity updated = productMapper.domainToEntityWithCategory(product, category);
            updated.setId(existing.getId());
            ProductEntity saved = productRepository.save(updated);
            return productMapper.entityToDomain(saved);
        } catch (Exception e) {
            log.error("Error updating product", e);
            throw new PersistenceException(e);
        }
    }

    @Override
    @Transactional
    public void deleteProduct(Long productId) {
        try {
            ProductEntity entity = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException(productId));  // Ensure ProductNotFoundException is thrown here
            productRepository.delete(entity);  // Proceed with deletion if the product is found
        } catch (ProductNotFoundException e) {
            // Log ProductNotFoundException if necessary
            log.error("Product not found with ID {}", productId, e);
            throw e;  // Rethrow the ProductNotFoundException
        } catch (Exception e) {
            log.error("Error deleting product with ID {}", productId, e);
            throw new PersistenceException(e);  // Wrap other exceptions in PersistenceException
        }
    }

}

