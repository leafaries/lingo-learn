package com.lingolearn.repo;

import com.lingolearn.entities.CategoryEntity;
import com.lingolearn.entities.VocabularySetEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class VocabularySetRepository extends BaseRepository<VocabularySetEntity, Long> {
    public VocabularySetRepository() {
        super(VocabularySetEntity.class);
    }

    public List<VocabularySetEntity> findByCategory(CategoryEntity category) {
        EntityManager em = dbManager.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT v FROM VocabularySetEntity v WHERE v.category = :category",
                            VocabularySetEntity.class)
                    .setParameter("category", category)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<VocabularySetEntity> findByName(String name) {
        EntityManager em = dbManager.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT v FROM VocabularySetEntity v WHERE LOWER(v.name) LIKE LOWER(:name)",
                            VocabularySetEntity.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
