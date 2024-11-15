package labs.spring.spacecatsecommerce.toggle.exception;

public class FeatureNotAvailableException extends RuntimeException {
    public FeatureNotAvailableException(String message) {
        super(message);
    }
}
