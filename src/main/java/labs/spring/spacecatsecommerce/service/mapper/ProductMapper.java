package labs.spring.spacecatsecommerce.service.mapper;

import labs.spring.spacecatsecommerce.domain.Product;
import labs.spring.spacecatsecommerce.dto.ProductDTO;
import labs.spring.spacecatsecommerce.repository.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.Iterator;
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

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "category", source = "category")
    Product toProduct(ProductDTO productDto);

    @Named("toProductDtoList")
    default List<ProductDTO> toProductDto(List<Product> products) {
        if (products == null) {
            return null;
        }

        return products.stream().map(this::toProductDto).toList();
    }

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "category", source = "category")
    Product fromProductEntity(ProductEntity productEntity);

    default List<Product> fromProductEntities(Iterator<ProductEntity> productEntityIterator) {
        List<Product> result = new ArrayList<>();
        productEntityIterator.forEachRemaining(
                (productEntity) -> {
                    result.add(fromProductEntity(productEntity));
                });
        return result;
    }
}

