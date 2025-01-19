package com.lingolearn.vocabulary.word.infra;

import com.lingolearn.common.infra.BaseRepository;
import com.lingolearn.vocabulary.word.domain.Difficulty;
import jakarta.persistence.NoResultException;

import java.util.List;
import java.util.Optional;

public class WordDatabaseImpl extends BaseRepository<WordEntity, Long> implements WordDatabase {
    public WordDatabaseImpl() {
        super(WordEntity.class);
    }

    @Override
    public WordEntity save(WordEntity entity) {
        return super.save(entity);
    }

    @Override
    public Optional<WordEntity> findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<WordEntity> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        findById(id).ifPresent(this::delete);
    }

    @Override
    public List<WordEntity> findByDifficulty(Difficulty difficulty) {
        return executeWithEntityManager(em -> {
            String jpql = "SELECT w FROM WordEntity w WHERE w.difficulty = :difficulty";
            var query = em.createQuery(jpql, WordEntity.class);
            query.setParameter("difficulty", difficulty);
            return query.getResultList();
        });
    }

    @Override
    public Optional<WordEntity> findByOriginalAndTranslation(String original, String translation) {
        return executeWithEntityManager(em -> {
            String jpql = "SELECT w FROM WordEntity w WHERE w.original = :original AND w.translation = :translation";
            var query = em.createQuery(jpql, WordEntity.class);
            query.setParameter("original", original);
            query.setParameter("translation", translation);
            try {
                return Optional.of(query.getSingleResult());
            } catch (NoResultException e) {
                return Optional.empty();
            }
        });
    }
}
