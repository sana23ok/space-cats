package labs.spring.spacecatsecommerce.service;

import labs.spring.spacecatsecommerce.domain.Product;
import labs.spring.spacecatsecommerce.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long productId);
    Product createProduct(Product product);
    Product updateProduct(Long productId, Product product);
    void deleteProduct(Long productId);
}
