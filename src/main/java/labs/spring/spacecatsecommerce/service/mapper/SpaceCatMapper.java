package labs.spring.spacecatsecommerce.service.mapper;

import labs.spring.spacecatsecommerce.domain.SpaceCat;
import labs.spring.spacecatsecommerce.dto.SpaceCatDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SpaceCatMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "planet", source = "planet")
    @Mapping(target = "breed", source = "breed")
    SpaceCatDTO toDTO(SpaceCat spaceCat);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "planet", source = "planet")
    @Mapping(target = "breed", source = "breed")
    SpaceCat toEntity(SpaceCatDTO spaceCatDTO);
}


