package labs.spring.spacecatsecommerce.service.mapper;

import labs.spring.spacecatsecommerce.domain.Product;
import labs.spring.spacecatsecommerce.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.List;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ProductMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "category", source = "category")
    ProductDTO toProductDto(Product product);
//    default ProductDTO toProductDto(Product product) {
//        if (product == null) {
//            return null;
//        }
//
//        return ProductDTO.builder()
//                .id(product.getId())
//                .name(product.getName())
//                .type(product.getType())
//                .price(product.getPrice())
//                .description(product.getDescription())
//                .build();
//    }

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "category", source = "category")
    Product toProduct(ProductDTO productDto);
//    default Product toProduct(ProductDTO productDto) {
//        if (productDto == null) {
//            return null;
//        }
//
//        return Product.builder()
//                .id(productDto.getId())
//                .name(productDto.getName())
//                .type(productDto.getType())
//                .price(productDto.getPrice())
//                .description(productDto.getDescription())
//                .build();
//    }

    @Named("toProductDtoList")
    default List<ProductDTO> toProductDto(List<Product> products) {
        if (products == null) {
            return null;
        }

        return products.stream().map(this::toProductDto).toList();
    }
}

