package labs.spring.spacecatsecommerce.service.exception;

public class OrderNotFoundException extends RuntimeException {

    private final String orderId;

    public OrderNotFoundException(String orderId) {
        super("Order with ID " + orderId + " not found.");
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
