package com.lingolearn.vocabulary.set.infra;

import com.lingolearn.NotImplementedException;
import com.lingolearn.common.infra.BaseRepository;
import com.lingolearn.vocabulary.set.adapter.VocabularySetDatabase;
import com.lingolearn.vocabulary.set.domain.VocabularySet;

import java.util.List;
import java.util.Optional;

public class JpaVocabularySetDatabaseImpl extends BaseRepository<VocabularySet, Long> implements VocabularySetDatabase {
    public JpaVocabularySetDatabaseImpl() {
        super(VocabularySet.class);
    }

    @Override
    public void save(VocabularySet set) {
        // TODO: Impl
        throw new NotImplementedException();

//        VocabularySetEntity entity = mapper.toEntity(set);
//        entity = super.save(entity);
//        return mapper.toDomain(entity);
    }

    @Override
    public Optional<VocabularySet> findById(Long id) {
        // TODO: Impl
        throw new NotImplementedException();

//        return super.findById(id)
//                .map(mapper::toDomain)
//                .orElseThrow(() -> new RuntimeException("VocabularySet not found"));
    }

    @Override
    public List<VocabularySet> findAll() {
        // TODO: Impl
        throw new NotImplementedException();

//        return super.findAll().stream()
//                .map(mapper::toDomain)
//                .toList();
    }

    @Override
    public void deleteById(Long id) {
        // TODO: Impl
        throw new NotImplementedException();

//        findById(id).ifPresent(this::delete);
    }

//    public void assignCategory(Long setId, Long categoryId) {
//        // TODO: Impl
//        throw new NotImplementedException();
//
//        EntityManager em = dbManager.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        try {
//            tx.begin();
//            VocabularySetEntity set = em.find(VocabularySetEntity.class, setId);
//            CategoryEntity category = em.find(CategoryEntity.class, categoryId);
//            set.setCategory(category);
//            em.merge(set);
//            tx.commit();
//        } catch (Exception e) {
//            if (tx != null && tx.isActive()) {
//                tx.rollback();
//            }
//            throw new RuntimeException("Failed to assign category", e);
//        } finally {
//            em.close();
//        }
//    }
}
