package labs.spring.spacecatsecommerce.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CosmicWordValidator.class)
public @interface CosmicWordCheck {
    String message() default "The name must contain a cosmic term like 'star', 'galaxy', or 'comet'.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
