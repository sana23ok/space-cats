package labs.spring.spacecatsecommerce.config;

import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "application.feature")
public class FeatureToggleProperties {

    private Map<String, Boolean> toggles;
    
    public boolean check(String featureToggle) {
        return toggles.getOrDefault(featureToggle, false);
    }
}
