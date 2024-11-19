package labs.spring.spacecatsecommerce.toggle;

import labs.spring.spacecatsecommerce.toggle.annotation.DisabledFeatureToggle;
import labs.spring.spacecatsecommerce.toggle.annotation.EnabledFeatureToggle;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

public class FeatureToggleExtension implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {

        context.getTestMethod().ifPresent(method -> {

            FeatureToggleService featureToggleService = getFeatureToggleService(context);

            if (method.isAnnotationPresent(EnabledFeatureToggle.class)) {
                EnabledFeatureToggle enabledFeatureToggleAnnotation = method.getAnnotation(EnabledFeatureToggle.class);
                FeatureToggles featureToggle = getFeatureToggleFromString(enabledFeatureToggleAnnotation.value().name());
                featureToggleService.enable(featureToggle);
            } else if (method.isAnnotationPresent(DisabledFeatureToggle.class)) {
                DisabledFeatureToggle disabledFeatureToggleAnnotation = method.getAnnotation(DisabledFeatureToggle.class);
                FeatureToggles featureToggle = getFeatureToggleFromString(disabledFeatureToggleAnnotation.value().name());
                featureToggleService.disable(featureToggle);
            }
        });
    }

    @Override
    public void afterEach(ExtensionContext context) {
        context.getTestMethod().ifPresent(method -> {
            FeatureToggleService featureToggleService = getFeatureToggleService(context);
            FeatureToggles featureToggle = null;

            if (method.isAnnotationPresent(EnabledFeatureToggle.class)) {
                EnabledFeatureToggle enabledFeatureToggleAnnotation = method.getAnnotation(EnabledFeatureToggle.class);
                featureToggle = getFeatureToggleFromString(enabledFeatureToggleAnnotation.value().name());
            } else if (method.isAnnotationPresent(DisabledFeatureToggle.class)) {
                DisabledFeatureToggle disabledFeatureToggleAnnotation = method.getAnnotation(DisabledFeatureToggle.class);
                featureToggle = getFeatureToggleFromString(disabledFeatureToggleAnnotation.value().name());
            }

            if (featureToggle != null) {
                if (getFeatureNamePropertyAsBoolean(context, featureToggle.name())) {
                    featureToggleService.enable(featureToggle);
                } else {
                    featureToggleService.disable(featureToggle);
                }
            }
        });
    }

    private FeatureToggles getFeatureToggleFromString(String featureName) {
        // Case-insensitive comparison
        for (FeatureToggles toggle : FeatureToggles.values()) {
            if (toggle.name().equalsIgnoreCase(featureName)) {
                return toggle;
            }
        }
        throw new IllegalArgumentException("No enum constant for feature: " + featureName);
    }

    private boolean getFeatureNamePropertyAsBoolean(ExtensionContext context, String featureName) {
        Environment environment = SpringExtension.getApplicationContext(context).getEnvironment();
        return environment.getProperty("application.feature.toggles." + featureName, Boolean.class, Boolean.FALSE);
    }

    private FeatureToggleService getFeatureToggleService(ExtensionContext context) {
        return SpringExtension.getApplicationContext(context).getBean(FeatureToggleService.class);
    }

}