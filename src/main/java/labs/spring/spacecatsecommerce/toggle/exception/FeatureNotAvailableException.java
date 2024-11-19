package labs.spring.spacecatsecommerce.toggle.exception;

public class FeatureNotAvailableException extends RuntimeException {
    public FeatureNotAvailableException(String featureName) {
        super("Feature '" + featureName + "' is not enabled.");
    }
}
