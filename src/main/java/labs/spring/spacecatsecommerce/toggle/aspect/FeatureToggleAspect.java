package labs.spring.spacecatsecommerce.toggle.aspect;

import labs.spring.spacecatsecommerce.config.FeatureToggleProperties;
import labs.spring.spacecatsecommerce.toggle.FeatureToggles;
import labs.spring.spacecatsecommerce.toggle.annotation.FeatureToggle;
import labs.spring.spacecatsecommerce.toggle.exception.FeatureNotAvailableException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
@RequiredArgsConstructor
public class FeatureToggleAspect {

    private final FeatureToggleProperties featureToggleProperties;

    @Before("@annotation(featureToggle)")
    public void checkFeatureToggle(FeatureToggle featureToggle) {
        FeatureToggles feature = featureToggle.value();
        String featureName = feature.getFeatureName();

        // Перевіряємо, чи увімкнена функція
        boolean isEnabled = featureToggleProperties.check(featureName);

        if (!isEnabled) {
            throw new FeatureNotAvailableException("Feature " + featureName + " is not enabled.");
        }
    }
}
