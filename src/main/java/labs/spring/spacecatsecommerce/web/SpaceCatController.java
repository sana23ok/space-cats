package labs.spring.spacecatsecommerce.web;

import labs.spring.spacecatsecommerce.service.SpaceCatService;
import labs.spring.spacecatsecommerce.toggle.FeatureToggleService;
import labs.spring.spacecatsecommerce.toggle.FeatureToggles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpaceCatController {

    private final SpaceCatService spaceCatService;
    private final FeatureToggleService featureToggleService;

    @Autowired
    public SpaceCatController(SpaceCatService spaceCatService, FeatureToggleService featureToggleService) {
        this.spaceCatService = spaceCatService;
        this.featureToggleService = featureToggleService;
    }

    @GetMapping("/api/v1/spaceCats")
    public ResponseEntity<List<String>> getSpaceCats() {
        // Отримання ключа фічі через FeatureToggles
        FeatureToggles toggle = FeatureToggles.COSMO_CATS;  // Використовуємо enum COSMO_CATS
        System.out.println("Checking feature in controller: " + toggle.getFeatureName());

        // Перевірка, чи увімкнена фіча
        boolean isEnabled = featureToggleService.check(toggle);
        System.out.println("Feature enabled status: " + isEnabled);

        // Якщо фіча вимкнена, повертаємо статус 404
        if (!isEnabled) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Якщо фіча увімкнена, повертаємо список космокотів
        return ResponseEntity.ok(spaceCatService.getSpaceCats());
    }
}


