package com.lingolearn.vocabulary.set.infra;

import com.lingolearn.common.infra.BaseRepository;
import com.lingolearn.vocabulary.set.domain.VocabularySet;
import com.lingolearn.vocabulary.set.usecase.VocabularySetRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JpaVocabularySetRepository extends BaseRepository<VocabularySetEntity, Long> implements VocabularySetRepository {
    private final VocabularySetMapper mapper;

    public JpaVocabularySetRepository(VocabularySetMapper mapper) {
        super(VocabularySetEntity.class);
        this.mapper = mapper;
    }

    @Override
    public VocabularySet save(VocabularySet set) {
        VocabularySetEntity entity = mapper.toEntity(set);
        entity = super.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public Optional<VocabularySet> findById(UUID id) {
        return super.findById(id)
                .map(mapper::toDomain)
                .orElseThrow(() -> new RuntimeException("VocabularySet not found"));
    }

    @Override
    public List<VocabularySet> findAll() {
        return super.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        findById(id).ifPresent(this::delete);
    }

    @Override
    public void assignCategory(UUID setId, UUID categoryId) {
        EntityManager em = dbManager.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            VocabularySetEntity set = em.find(VocabularySetEntity.class, setId);
            CategoryEntity category = em.find(CategoryEntity.class, categoryId);
            set.setCategory(category);
            em.merge(set);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Failed to assign category", e);
        } finally {
            em.close();
        }
    }
}
