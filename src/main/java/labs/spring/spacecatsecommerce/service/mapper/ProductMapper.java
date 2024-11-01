package labs.spring.spacecatsecommerce.service.mapper;

import labs.spring.spacecatsecommerce.domain.Product;
import labs.spring.spacecatsecommerce.dto.ProductDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    default ProductDTO toProductDto(Product product) {
        if (product == null) {
            return null;
        }

        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .type(product.getType())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }

    default Product toProduct(ProductDTO productDto) {
        if (productDto == null) {
            return null;
        }

        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .type(productDto.getType())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .build();
    }

    default List<ProductDTO> toProductDto(List<Product> products) {
        if (products == null) {
            return null;
        }

        return products.stream().map(this::toProductDto).toList();
    }
}

