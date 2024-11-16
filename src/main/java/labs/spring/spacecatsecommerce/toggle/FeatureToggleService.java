package labs.spring.spacecatsecommerce.toggle;

import labs.spring.spacecatsecommerce.config.FeatureToggleProperties;
import org.springframework.stereotype.Service;

@Service
public class FeatureToggleService {

    private final FeatureToggleProperties featureToggleProperties;

    public FeatureToggleService(FeatureToggleProperties featureToggleProperties) {
        this.featureToggleProperties = featureToggleProperties;
    }

    public boolean check(FeatureToggles toggle) {
        String featureName = toggle.getFeatureName();
        System.out.println("Checking feature: " + featureName);
        return featureToggleProperties.check(featureName);
    }

    public void enable(FeatureToggles toggle) {
        String featureName = toggle.getFeatureName();
        System.out.println("Enabling feature: " + featureName);
        featureToggleProperties.getToggles().put(featureName, true);
    }

    public void disable(FeatureToggles toggle) {
        String featureName = toggle.getFeatureName();
        System.out.println("Disabling feature: " + featureName);
        featureToggleProperties.getToggles().put(featureName, false);
    }
}
