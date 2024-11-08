package labs.spring.spacecatsecommerce.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class CosmicWordValidator implements ConstraintValidator<CosmicWordCheck, String> {

    private final List<String> cosmicTerms = Arrays.asList(
            "star", "galaxy", "comet", "planet", "nebula", "supernova", "black hole",
            "quasar", "asteroid", "meteor", "constellation", "universe", "solar system",
            "exoplanet", "pulsar"
    );

    @Override
    public void initialize(CosmicWordCheck constraintAnnotation) {
        // Ініціалізація, якщо потрібно
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return cosmicTerms.stream().anyMatch(value.toLowerCase()::contains);
    }


}
