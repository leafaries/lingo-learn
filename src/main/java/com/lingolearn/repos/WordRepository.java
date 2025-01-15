package com.lingolearn.repos;

import com.lingolearn.entities.WordEntity;
import com.lingolearn.enums.Difficulty;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class WordRepository extends BaseRepository<WordEntity, Long> {
    public WordRepository() {
        super(WordEntity.class);
    }

    public List<WordEntity> findByDifficulty(Difficulty difficulty) {
        try (EntityManager em = dbManager.createEntityManager()) {
            return em.createQuery(
                            "SELECT w FROM WordEntity w WHERE w.difficulty = :difficulty",
                            WordEntity.class)
                    .setParameter("difficulty", difficulty)
                    .getResultList();
        }
    }

    public Optional<WordEntity> findByOriginalAndTranslation(String original, String translation) {
        try (EntityManager em = dbManager.createEntityManager()) {
            List<WordEntity> results = em.createQuery(
                            "SELECT w FROM WordEntity w WHERE w.original = :original AND w.translation = :translation",
                            WordEntity.class)
                    .setParameter("original", original)
                    .setParameter("translation", translation)
                    .getResultList();
            return results.isEmpty() ? Optional.empty() : Optional.of(results.getFirst());
        }
    }

    public List<WordEntity> findProblematicWords(double errorThreshold) {
        try (EntityManager em = dbManager.createEntityManager()) {
            return em.createQuery(
                            "SELECT w FROM WordEntity w " +
                                    "WHERE w.id IN (" +
                                    "SELECT a.word.id FROM AnswerEntity a " +
                                    "GROUP BY a.word.id " +
                                    "HAVING CAST(COUNT(CASE WHEN a.correct = false THEN 1 ELSE null END) AS double) / " +
                                    "CAST(COUNT(a) AS double) > :threshold" +
                                    ")",
                            WordEntity.class)
                    .setParameter("threshold", errorThreshold)
                    .getResultList();
        }
    }
}
