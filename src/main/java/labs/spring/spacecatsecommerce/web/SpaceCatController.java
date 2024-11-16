package labs.spring.spacecatsecommerce.web;

import labs.spring.spacecatsecommerce.domain.SpaceCat;
import labs.spring.spacecatsecommerce.service.SpaceCatService;
import labs.spring.spacecatsecommerce.toggle.FeatureToggleService;
import labs.spring.spacecatsecommerce.toggle.FeatureToggles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/spaceCats")
public class SpaceCatController {

    private final SpaceCatService spaceCatService;
    private final FeatureToggleService featureToggleService;

    @Autowired
    public SpaceCatController(SpaceCatService spaceCatService, FeatureToggleService featureToggleService) {
        this.spaceCatService = spaceCatService;
        this.featureToggleService = featureToggleService;
    }

    @GetMapping
    public ResponseEntity<List<SpaceCat>> getAllSpaceCats() {
        FeatureToggles toggle = FeatureToggles.COSMO_CATS;

        if (!featureToggleService.check(toggle)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(spaceCatService.getAllSpaceCats());
    }

    /**
     * Отримати конкретного SpaceCat за ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SpaceCat> getSpaceCatById(@PathVariable Long id) {
        FeatureToggles toggle = FeatureToggles.COSMO_CATS;

        if (!featureToggleService.check(toggle)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        try {
            SpaceCat spaceCat = spaceCatService.getSpaceCatById(id);
            return ResponseEntity.ok(spaceCat);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}



