//package labs.spring.spacecatsecommerce.toggle;
//
//import labs.spring.spacecatsecommerce.toggle.annotation.DisabledFeatureToggle;
//import labs.spring.spacecatsecommerce.toggle.annotation.EnabledFeatureToggle;
//import labs.spring.spacecatsecommerce.service.SpaceCatService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@SpringBootTest
//@ExtendWith(FeatureToggleExtension.class)
//class FeatureToggleTests {
//
//    @Autowired
//    private SpaceCatService spaceCatService;
//
//    @Test
//    @EnabledFeatureToggle(FeatureToggles.COSMO_CATS)
//    void shouldAllowAccessWhenFeatureEnabled() {
//        var spaceCats = spaceCatService.getSpaceCats();
//        assertThat(spaceCats).containsExactly("CosmoCat1", "CosmoCat2", "CosmoCat3");
//    }
//
//    @Test
//    @DisabledFeatureToggle(FeatureToggles.COSMO_CATS)
//    void shouldThrowExceptionWhenFeatureDisabled() {
//        assertThrows(RuntimeException.class, () -> spaceCatService.getSpaceCats());
//    }
//}
