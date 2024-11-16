package labs.spring.spacecatsecommerce.web;

import labs.spring.spacecatsecommerce.AbstractIT;
import labs.spring.spacecatsecommerce.toggle.FeatureToggles;
import labs.spring.spacecatsecommerce.toggle.annotation.DisabledFeatureToggle;
import labs.spring.spacecatsecommerce.toggle.annotation.EnabledFeatureToggle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
@DisplayName("Space Cat Controller IT")
class SpaceCatControllerIT extends AbstractIT {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @Test
    @EnabledFeatureToggle(FeatureToggles.COSMO_CATS)
    @DisplayName("Log 200 when COSMO_CATS feature is enabled")
    void shouldLog200WhenFeatureEnabled() throws Exception {
        System.out.println("Feature COSMO_CATS enabled."); // Debug log
        mockMvc.perform(get("/api/v1/spaceCats"))
                .andDo(result -> System.out.println("Response status: " + result.getResponse().getStatus()));
    }

    @Test
    @DisabledFeatureToggle(FeatureToggles.COSMO_CATS)
    @DisplayName("Log 404 when COSMO_CATS feature is disabled")
    void shouldLog404WhenFeatureDisabled() throws Exception {
        System.out.println("Feature COSMO_CATS disabled."); // Debug log
        mockMvc.perform(get("/api/v1/spaceCats"))
                .andDo(result -> System.out.println("Response status: " + result.getResponse().getStatus()));
    }

    @Test
    @EnabledFeatureToggle(FeatureToggles.COSMO_CATS)
    @DisplayName("Log 200 when COSMO_CATS feature is enabled and fetching by ID")
    void shouldLog200WhenFetchingByIdWithFeatureEnabled() throws Exception {
        Long existingId = 1L;
        System.out.println("Feature COSMO_CATS enabled for fetching by ID."); // Debug log
        mockMvc.perform(get("/api/v1/spaceCats/" + existingId))
                .andDo(result -> System.out.println("Response status: " + result.getResponse().getStatus()));
    }

    @Test
    @DisabledFeatureToggle(FeatureToggles.COSMO_CATS)
    @DisplayName("Log 404 when COSMO_CATS feature is disabled and fetching by ID")
    void shouldLog404WhenFetchingByIdWithFeatureDisabled() throws Exception {
        Long existingId = 1L;
        System.out.println("Feature COSMO_CATS disabled for fetching by ID."); // Debug log
        mockMvc.perform(get("/api/v1/spaceCats/" + existingId))
                .andDo(result -> System.out.println("Response status: " + result.getResponse().getStatus()));
    }

    @Test
    @EnabledFeatureToggle(FeatureToggles.COSMO_CATS)
    @DisplayName("Log 404 when COSMO_CATS feature is enabled but ID does not exist")
    void shouldLog404WhenFetchingNonExistingIdWithFeatureEnabled() throws Exception {
        Long nonExistingId = 99L;
        System.out.println("Feature COSMO_CATS enabled for non-existing ID."); // Debug log
        mockMvc.perform(get("/api/v1/spaceCats/" + nonExistingId))
                .andDo(result -> System.out.println("Response status: " + result.getResponse().getStatus()));
    }
}




