package labs.spring.spacecatsecommerce.service.impl;

import labs.spring.spacecatsecommerce.domain.SpaceCat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SpaceCatServiceImplTest {

    private SpaceCatServiceImpl spaceCatService;

    @BeforeEach
    void setUp() {
        spaceCatService = new SpaceCatServiceImpl();
    }

    @Test
    void getAllSpaceCats_ShouldReturnAllSpaceCats() {
        // When
        List<SpaceCat> spaceCats = spaceCatService.getAllSpaceCats();

        // Then
        assertNotNull(spaceCats, "SpaceCats list should not be null");
        assertEquals(5, spaceCats.size(), "There should be 5 SpaceCats");
        assertEquals("Nebula", spaceCats.get(0).getName(), "First SpaceCat's name should be 'Nebula'");
    }

    @Test
    void getSpaceCatById_ShouldReturnSpaceCat_WhenIdExists() {
        // Given
        Long existingId = 1L;

        // When
        SpaceCat spaceCat = spaceCatService.getSpaceCatById(existingId);

        // Then
        assertNotNull(spaceCat, "SpaceCat should not be null");
        assertEquals("Nebula", spaceCat.getName(), "SpaceCat's name should be 'Nebula'");
        assertEquals("Saturn", spaceCat.getPlanet(), "SpaceCat's planet should be 'Saturn'");
        assertEquals("AstroFeline", spaceCat.getBreed(), "SpaceCat's breed should be 'AstroFeline'");
    }

    @Test
    void getSpaceCatById_ShouldThrowException_WhenIdDoesNotExist() {
        // Given
        Long nonExistingId = 99L;

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            spaceCatService.getSpaceCatById(nonExistingId);
        });
        assertEquals("SpaceCat not found with id: " + nonExistingId, exception.getMessage());
    }
}
