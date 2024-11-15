package labs.spring.spacecatsecommerce.web;

import labs.spring.spacecatsecommerce.service.SpaceCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpaceCatController {

    private final SpaceCatService spaceCatService;

    @Autowired
    public SpaceCatController(SpaceCatService cosmoCatService) {
        this.spaceCatService = cosmoCatService;
    }

    @GetMapping("/api/v1/spaceCats")
    public List<String> getCosmoCats() {
        return spaceCatService.getSpaceCats();
    }
}
