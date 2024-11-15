package labs.spring.spacecatsecommerce.toggle;

public enum FeatureToggles {
    COSMO_CATS("feature.cosmoCats.enabled"),
    KITTY_PRODUCTS("feature.kittyProducts.enabled");

    private final String key;

    FeatureToggles(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
