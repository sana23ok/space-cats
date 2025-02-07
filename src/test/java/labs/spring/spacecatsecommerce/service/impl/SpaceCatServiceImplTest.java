package labs.spring.spacecatsecommerce.service.impl;

import labs.spring.spacecatsecommerce.domain.SpaceCat;
import labs.spring.spacecatsecommerce.dto.SpaceCatDTO;
import labs.spring.spacecatsecommerce.service.mapper.SpaceCatMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


class SpaceCatServiceImplTest {

    private SpaceCatServiceImpl spaceCatService;

    @BeforeEach
    void setUp() {
        SpaceCatMapper spaceCatMapper = Mappers.getMapper(SpaceCatMapper.class);
        spaceCatService = new SpaceCatServiceImpl(spaceCatMapper);
    }

    @Test
    void getAllSpaceCats_ShouldReturnAllSpaceCats() {
        List<SpaceCatDTO> result = spaceCatService.getAllSpaceCats();

        assertNotNull(result);
        assertEquals(5, result.size());
        assertEquals("Nebula", result.get(0).getName());
        assertEquals("Saturn", result.get(0).getPlanet());
    }

    @Test
    void getSpaceCatById_ValidId_ShouldReturnSpaceCat() {
        Long id = 1L;

        SpaceCat result = spaceCatService.getSpaceCatById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Nebula", result.getName());
    }

    @Test
    void getSpaceCatById_InvalidId_ShouldThrowException() {
        Long invalidId = 999L;

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> spaceCatService.getSpaceCatById(invalidId));

        assertEquals("SpaceCat not found with id: 999", exception.getMessage());
    }
}
