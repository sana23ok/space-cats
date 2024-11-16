package labs.spring.spacecatsecommerce.domain;

import lombok.*;

@Value
@Builder(toBuilder = true)
public class SpaceCat {

    Long id;
    String name;
    String planet;
    String breed;

}

