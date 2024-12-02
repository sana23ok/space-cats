package labs.spring.spacecatsecommerce.service.mapper;

import labs.spring.spacecatsecommerce.domain.SpaceCat;
import labs.spring.spacecatsecommerce.dto.SpaceCatDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpaceCatMapperTest {

    private SpaceCatMapper spaceCatMapper;

    @BeforeEach
    void setUp() {
        spaceCatMapper = new SpaceCatMapperImpl();
    }

    @Test
    void toDTO_ShouldMapSpaceCatEntityToSpaceCatDTO() {
        SpaceCat spaceCat = SpaceCat.builder()
                .id(1L)
                .name("Galactic Whiskers")
                .planet("Planet Zeta")
                .breed("Cosmic Tiger")
                .build();

        SpaceCatDTO spaceCatDTO = spaceCatMapper.toDTO(spaceCat);

        assertThat(spaceCatDTO).isNotNull();
        assertThat(spaceCatDTO).isInstanceOf(SpaceCatDTO.class);
        assertThat(spaceCatDTO.getId()).isEqualTo(spaceCat.getId());
        assertThat(spaceCatDTO.getName()).isEqualTo(spaceCat.getName());
        assertThat(spaceCatDTO.getPlanet()).isEqualTo(spaceCat.getPlanet());
        assertThat(spaceCatDTO.getBreed()).isEqualTo(spaceCat.getBreed());
    }

    @Test
    void toDTO_NullSpaceCatEntity_ShouldReturnNull() {
        SpaceCatDTO spaceCatDTO = spaceCatMapper.toDTO(null);
        assertThat(spaceCatDTO).isNull();
    }

    @Test
    void toEntity_ShouldMapSpaceCatDTOToSpaceCatEntity() {
        SpaceCatDTO spaceCatDTO = SpaceCatDTO.builder()
                .id(2L)
                .name("Star Paws")
                .planet("Planet Alpha")
                .breed("Nebula Purr")
                .build();

        SpaceCat spaceCat = spaceCatMapper.toEntity(spaceCatDTO);

        assertThat(spaceCat).isNotNull();
        assertThat(spaceCat).isInstanceOf(SpaceCat.class);
        assertThat(spaceCat.getId()).isEqualTo(spaceCatDTO.getId());
        assertThat(spaceCat.getName()).isEqualTo(spaceCatDTO.getName());
        assertThat(spaceCat.getPlanet()).isEqualTo(spaceCatDTO.getPlanet());
        assertThat(spaceCat.getBreed()).isEqualTo(spaceCatDTO.getBreed());
    }

    @Test
    void toEntity_NullSpaceCatDTO_ShouldReturnNull() {
        SpaceCat spaceCat = spaceCatMapper.toEntity(null);
        assertThat(spaceCat).isNull();
    }
}

