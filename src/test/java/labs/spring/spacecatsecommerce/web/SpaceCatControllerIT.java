package labs.spring.spacecatsecommerce.web;

import labs.spring.spacecatsecommerce.AbstractIT;
import labs.spring.spacecatsecommerce.toggle.FeatureToggleExtension;
import labs.spring.spacecatsecommerce.toggle.FeatureToggles;
import labs.spring.spacecatsecommerce.toggle.annotation.DisabledFeatureToggle;
import labs.spring.spacecatsecommerce.toggle.annotation.EnabledFeatureToggle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@DisplayName("Space Cats Controller IT")
@ExtendWith(FeatureToggleExtension.class)
class SpaceCatControllerIT extends AbstractIT {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @Test
    @EnabledFeatureToggle(FeatureToggles.COSMO_CATS)
    @DisplayName("200 if COSMO_CATS feature is enabled")
    void shouldReturn200WhenCosmoCatsEnabled() throws Exception {
        mockMvc.perform(get("/api/v1/spaceCats")).andExpect(status().isOk());
    }

    @Test
    @DisabledFeatureToggle(FeatureToggles.COSMO_CATS)
    @DisplayName("404 if COSMO_CATS feature is disabled")
    void shouldReturn404WhenCosmoCatsDisabled() throws Exception {
        mockMvc.perform(get("/api/v1/spaceCats")).andExpect(status().isNotFound());
    }
}

