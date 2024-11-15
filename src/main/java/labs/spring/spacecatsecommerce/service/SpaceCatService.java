package labs.spring.spacecatsecommerce.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpaceCatService {

    public List<String> getSpaceCats() {

        return List.of("CosmoCat1", "CosmoCat2", "CosmoCat3");

    }

}
