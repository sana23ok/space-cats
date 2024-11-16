package labs.spring.spacecatsecommerce.service;

import org.springframework.stereotype.Service;
import java.util.List;
import labs.spring.spacecatsecommerce.domain.SpaceCat;


@Service
public interface SpaceCatService {

    List<SpaceCat> getAllSpaceCats();
    SpaceCat getSpaceCatById(Long spaceCatId);

}