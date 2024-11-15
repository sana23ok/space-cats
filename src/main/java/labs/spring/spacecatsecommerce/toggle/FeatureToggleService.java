package labs.spring.spacecatsecommerce.toggle;

import labs.spring.spacecatsecommerce.config.FeatureToggleProperties;
import org.springframework.stereotype.Service;

@Service
public class FeatureToggleService {

    private final FeatureToggleProperties featureToggleProperties;

    public FeatureToggleService(FeatureToggleProperties featureToggleProperties) {
        this.featureToggleProperties = featureToggleProperties;
    }

    // Перевірка, чи фіча увімкнена
    public boolean check(String featureName) {
        return featureToggleProperties.check(featureName);
    }

    // Увімкнення фічі
    public void enable(String featureName) {
        featureToggleProperties.getToggles().put(featureName, true);
    }

    // Вимкнення фічі
    public void disable(String featureName) {
        featureToggleProperties.getToggles().put(featureName, false);
    }
}



