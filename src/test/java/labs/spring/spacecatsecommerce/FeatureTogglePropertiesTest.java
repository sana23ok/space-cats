package labs.spring.spacecatsecommerce;

import labs.spring.spacecatsecommerce.config.FeatureToggleProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FeatureTogglePropertiesTest {
    @Autowired
    private FeatureToggleProperties properties;

    @Test
    void shouldLoadFeatureToggles() {
        System.out.println("Toggles: " + properties.getToggles());
    }
}

