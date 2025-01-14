package com.lingolearn.repo;

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
        EntityManager em = dbManager.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT w FROM WordEntity w WHERE w.difficulty = :difficulty",
                            WordEntity.class)
                    .setParameter("difficulty", difficulty)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<WordEntity> findByOriginalAndTranslation(String original, String translation) {
        EntityManager em = dbManager.createEntityManager();
        try {
            List<WordEntity> results = em.createQuery(
                            "SELECT w FROM WordEntity w WHERE w.original = :original AND w.translation = :translation",
                            WordEntity.class)
                    .setParameter("original", original)
                    .setParameter("translation", translation)
                    .getResultList();
            return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
        } finally {
            em.close();
        }
    }

    public List<WordEntity> findProblematicWords(double errorThreshold) {
        EntityManager em = dbManager.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT w FROM WordEntity w " +
                                    "WHERE (SELECT COUNT(a) FROM AnswerEntity a WHERE a.word = w AND a.correct = false) / " +
                                    "      (SELECT COUNT(a) FROM AnswerEntity a WHERE a.word = w) > :threshold",
                            WordEntity.class)
                    .setParameter("threshold", errorThreshold)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
