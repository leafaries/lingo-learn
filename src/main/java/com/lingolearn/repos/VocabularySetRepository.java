package com.lingolearn.repos;

import com.lingolearn.entities.CategoryEntity;
import com.lingolearn.entities.VocabularySetEntity;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class VocabularySetRepository extends BaseRepository<VocabularySetEntity, Long> {
    public VocabularySetRepository() {
        super(VocabularySetEntity.class);
    }

    public List<VocabularySetEntity> findByCategory(CategoryEntity category) {
        try (EntityManager em = dbManager.createEntityManager()) {
            return em.createQuery(
                            "SELECT v FROM VocabularySetEntity v WHERE v.category = :category",
                            VocabularySetEntity.class)
                    .setParameter("category", category)
                    .getResultList();
        }
    }

    public Optional<VocabularySetEntity> findByName(String name) {
        try (EntityManager em = dbManager.createEntityManager()) {
            List<VocabularySetEntity> results = em.createQuery(
                            "SELECT v FROM VocabularySetEntity v WHERE LOWER(v.name) LIKE LOWER(:name)",
                            VocabularySetEntity.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
            return results.isEmpty() ? Optional.empty() : Optional.of(results.getFirst());
        }
    }
}
