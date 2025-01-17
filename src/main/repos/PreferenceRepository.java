package repos;

import com.lingolearn.BaseRepository;
import horizontallayers.infrastructure.jpaentities.PreferenceEntity;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class PreferenceRepository extends BaseRepository<PreferenceEntity, Long> {
    public PreferenceRepository() {
        super(PreferenceEntity.class);
    }

    public Optional<PreferenceEntity> findLatest() {
        EntityManager em = dbManager.createEntityManager();
        try {
            List<PreferenceEntity> results = em.createQuery(
                            "SELECT p FROM PreferenceEntity p ORDER BY p.lastModified DESC",
                            PreferenceEntity.class)
                    .setMaxResults(1)
                    .getResultList();
            return results.isEmpty() ? Optional.empty() : Optional.of(results.getFirst());
        } finally {
            em.close();
        }
    }
}
