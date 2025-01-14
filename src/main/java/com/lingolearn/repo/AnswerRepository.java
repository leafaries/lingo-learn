package com.lingolearn.repo;

import com.lingolearn.entities.AnswerEntity;
import com.lingolearn.entities.SessionEntity;
import com.lingolearn.entities.WordEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AnswerRepository extends BaseRepository<AnswerEntity, Long> {
    public AnswerRepository() {
        super(AnswerEntity.class);
    }

    public List<AnswerEntity> findBySession(SessionEntity session) {
        EntityManager em = dbManager.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT a FROM AnswerEntity a WHERE a.session = :session ORDER BY a.timestamp",
                            AnswerEntity.class)
                    .setParameter("session", session)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<AnswerEntity> findByWord(WordEntity word) {
        EntityManager em = dbManager.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT a FROM AnswerEntity a WHERE a.word = :word ORDER BY a.timestamp DESC",
                            AnswerEntity.class)
                    .setParameter("word", word)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public double getWordAccuracy(WordEntity word) {
        EntityManager em = dbManager.createEntityManager();
        try {
            Long totalAnswers = (Long) em.createQuery(
                            "SELECT COUNT(a) FROM AnswerEntity a WHERE a.word = :word")
                    .setParameter("word", word)
                    .getSingleResult();

            if (totalAnswers == 0) return 0.0;

            Long correctAnswers = (Long) em.createQuery(
                            "SELECT COUNT(a) FROM AnswerEntity a WHERE a.word = :word AND a.correct = true")
                    .setParameter("word", word)
                    .getSingleResult();

            return (double) correctAnswers / totalAnswers;
        } finally {
            em.close();
        }
    }
}
