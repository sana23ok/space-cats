package labs.spring.spacecatsecommerce.toggle;

import labs.spring.spacecatsecommerce.config.FeatureToggleProperties;
import labs.spring.spacecatsecommerce.toggle.FeatureToggleService;
import labs.spring.spacecatsecommerce.toggle.annotation.DisabledFeatureToggle;
import labs.spring.spacecatsecommerce.toggle.annotation.EnabledFeatureToggle;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

public class FeatureToggleExtension implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        context.getTestMethod().ifPresent(method -> {
            FeatureToggleService featureToggleService = getFeatureToggleService(context);

            if (method.isAnnotationPresent(EnabledFeatureToggle.class)) {
                EnabledFeatureToggle enabled = method.getAnnotation(EnabledFeatureToggle.class);
                featureToggleService.enable(enabled.value());
                System.out.println("Feature enabled: " + enabled.value()); // Debug log
            } else if (method.isAnnotationPresent(DisabledFeatureToggle.class)) {
                DisabledFeatureToggle disabled = method.getAnnotation(DisabledFeatureToggle.class);
                featureToggleService.disable(disabled.value());
                System.out.println("Feature disabled: " + disabled.value()); // Debug log
            }
        });
    }

    @Override
    public void afterEach(ExtensionContext context) {
        context.getTestMethod().ifPresent(method -> {
            FeatureToggleService featureToggleService = getFeatureToggleService(context);
            FeatureToggleProperties properties = getFeatureToggleProperties(context);

            for (String key : properties.getToggles().keySet()) {
                boolean originalState = properties.check(key);
                if (originalState) {
                    featureToggleService.enable(FeatureToggles.valueOf(key.toUpperCase()));
                    System.out.println("Restoring feature to enabled: " + key); // Debug log
                } else {
                    featureToggleService.disable(FeatureToggles.valueOf(key.toUpperCase()));
                    System.out.println("Restoring feature to disabled: " + key); // Debug log
                }
            }
        });
    }

    private FeatureToggleService getFeatureToggleService(ExtensionContext context) {
        return SpringExtension.getApplicationContext(context).getBean(FeatureToggleService.class);
    }

    private FeatureToggleProperties getFeatureToggleProperties(ExtensionContext context) {
        return SpringExtension.getApplicationContext(context).getBean(FeatureToggleProperties.class);
    }
}
