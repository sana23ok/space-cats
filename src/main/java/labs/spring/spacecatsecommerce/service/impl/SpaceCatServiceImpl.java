package labs.spring.spacecatsecommerce.service.impl;

import labs.spring.spacecatsecommerce.domain.SpaceCat;
import labs.spring.spacecatsecommerce.service.SpaceCatService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class SpaceCatServiceImpl implements SpaceCatService {

    private final List<SpaceCat> spaceCats = buildAllSpaceCatsMock();

    public List<SpaceCat> getAllSpaceCats() {
        return spaceCats;
    }

    public SpaceCat getSpaceCatById(Long spaceCatId) {
        return spaceCats.stream()
                .filter(spaceCat -> spaceCat.getId().equals(spaceCatId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("SpaceCat not found with id: " + spaceCatId));
    }

    private List<SpaceCat> buildAllSpaceCatsMock() {
        return new ArrayList<>(List.of(
                SpaceCat.builder().id(1L).name("Nebula").planet("Saturn").breed("AstroFeline").build(),
                SpaceCat.builder().id(2L).name("Orion").planet("Mars").breed("StarPaw").build(),
                SpaceCat.builder().id(3L).name("Andromeda").planet("Venus").breed("CometTail").build(),
                SpaceCat.builder().id(4L).name("Galaxia").planet("Pluto").breed("QuasarWhisker").build(),
                SpaceCat.builder().id(5L).name("Luna").planet("Earth").breed("LunarKitten").build()
        ));
    }
}
