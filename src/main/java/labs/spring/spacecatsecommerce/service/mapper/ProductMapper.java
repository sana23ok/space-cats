package labs.spring.spacecatsecommerce.service.mapper;

import labs.spring.spacecatsecommerce.domain.Product;
import labs.spring.spacecatsecommerce.dto.ProductDTO;
import labs.spring.spacecatsecommerce.repository.entity.CategoryEntity;
import labs.spring.spacecatsecommerce.repository.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ProductMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "category", source = "category")
    Product entityToDomain(ProductEntity productEntity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "category", source = "category")
    Product dtoToDomain(ProductDTO productDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "category", source = "category")
    ProductDTO domainToDto(Product product);

    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "type", source = "product.type")
    @Mapping(target = "price", source = "product.price")
    @Mapping(target = "description", source = "product.description")
    @Mapping(target = "category", source = "category")
    ProductEntity domainToEntityWithCategory(Product product, CategoryEntity category);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "category", source = "category")
    ProductEntity domainToEntity(Product product);
}
