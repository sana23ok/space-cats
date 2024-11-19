package labs.spring.spacecatsecommerce.web;

import labs.spring.spacecatsecommerce.domain.SpaceCat;
import labs.spring.spacecatsecommerce.dto.SpaceCatDTO;
import labs.spring.spacecatsecommerce.service.SpaceCatService;
import labs.spring.spacecatsecommerce.service.mapper.SpaceCatMapper;
import labs.spring.spacecatsecommerce.toggle.FeatureToggles;
import labs.spring.spacecatsecommerce.toggle.annotation.FeatureToggle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/spaceCats")
public class SpaceCatController {

    private final SpaceCatService spaceCatService;
    private final SpaceCatMapper spaceCatMapper;

    @Autowired
    public SpaceCatController(SpaceCatService spaceCatService, SpaceCatMapper spaceCatMapper) {
        this.spaceCatService = spaceCatService;
        this.spaceCatMapper = spaceCatMapper;
    }

    @GetMapping
    @FeatureToggle(FeatureToggles.COSMO_CATS)
    public ResponseEntity<List<SpaceCatDTO>> getAllCosmoCats() {
        List<SpaceCatDTO> spaceCats = spaceCatService.getAllSpaceCats();
        return ResponseEntity.ok(spaceCats);
    }

    @GetMapping("/{id}")
    @FeatureToggle(FeatureToggles.COSMO_CATS)
    public ResponseEntity<SpaceCatDTO> getCosmoCatById(@PathVariable Long id) {
        SpaceCat spaceCat = spaceCatService.getSpaceCatById(id);
        SpaceCatDTO spaceCatDTO = spaceCatMapper.toDTO(spaceCat);
        return ResponseEntity.ok(spaceCatDTO);
    }
}
