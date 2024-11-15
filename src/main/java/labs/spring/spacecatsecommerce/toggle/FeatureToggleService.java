package labs.spring.spacecatsecommerce.toggle;

import labs.spring.spacecatsecommerce.config.FeatureToggleProperties;
import org.springframework.stereotype.Service;

@Service
public class FeatureToggleService {

    private final FeatureToggleProperties featureToggleProperties;

    public FeatureToggleService(FeatureToggleProperties featureToggleProperties) {
        this.featureToggleProperties = featureToggleProperties;
    }

    public boolean isFeatureEnabled(String featureName) {
        return featureToggleProperties.check(featureName);
    }
}


