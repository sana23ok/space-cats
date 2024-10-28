package labs.spring.spacecatsecommerce.service.mapper;

import labs.spring.spacecatsecommerce.domain.Product;
import labs.spring.spacecatsecommerce.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "description", source = "description")
    ProductDTO toProductDto(Product product);

    Product toProduct(ProductDTO productDto);

    List<ProductDTO> toProductDto(List<Product> products);
}

