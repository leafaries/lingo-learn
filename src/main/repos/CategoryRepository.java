package repos;

import com.lingolearn.BaseRepository;
import horizontallayers.infrastructure.jpaentities.CategoryEntity;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class CategoryRepository extends BaseRepository<CategoryEntity, Long> {
    public CategoryRepository() {
        super(CategoryEntity.class);
    }

    public Optional<CategoryEntity> findByName(String name) {
        EntityManager em = dbManager.createEntityManager();
        try {
            List<CategoryEntity> results = em.createQuery(
                            "SELECT c FROM CategoryEntity c WHERE c.name = :name",
                            CategoryEntity.class)
                    .setParameter("name", name)
                    .getResultList();
            return results.isEmpty() ? Optional.empty() : Optional.of(results.getFirst());
        } finally {
            em.close();
        }
    }
}
