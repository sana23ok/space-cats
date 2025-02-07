package labs.spring.spacecatsecommerce.service;

import labs.spring.spacecatsecommerce.dto.SpaceCatDTO;
import org.springframework.stereotype.Service;
import java.util.List;
import labs.spring.spacecatsecommerce.domain.SpaceCat;


@Service
public interface SpaceCatService {
    List<SpaceCatDTO> getAllSpaceCats();
    SpaceCat getSpaceCatById(Long spaceCatId);
}