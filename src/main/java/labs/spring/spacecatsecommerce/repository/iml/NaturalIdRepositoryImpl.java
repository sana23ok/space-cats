package labs.spring.spacecatsecommerce.repository.iml;

import jakarta.persistence.EntityManager;
import java.io.Serializable;
import java.util.Optional;

import labs.spring.spacecatsecommerce.repository.NaturalIdRepository;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class NaturalIdRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements NaturalIdRepository<T, ID> {

    private final EntityManager entityManager;

    public NaturalIdRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<T> findByNaturalId(ID naturalId) {
        return entityManager.unwrap(Session.class).bySimpleNaturalId(this.getDomainClass())
                .loadOptional(naturalId);
    }

    @Override
    public void deleteByNaturalId(ID naturalId) {
        findByNaturalId(naturalId).ifPresent(this::delete);
    }

}