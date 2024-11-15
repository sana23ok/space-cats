package labs.spring.spacecatsecommerce.toggle.aspect;

import labs.spring.spacecatsecommerce.config.FeatureToggleProperties;
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
        String toggleName = featureToggle.value().name();
        boolean isEnabled = featureToggleProperties.check(toggleName);

        if (!isEnabled) {
            throw new FeatureNotAvailableException("Feature "
                    + toggleName + " is not enabled.");
        }
    }
}


