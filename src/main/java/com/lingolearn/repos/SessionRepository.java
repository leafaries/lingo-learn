package com.lingolearn.repos;

import com.lingolearn.entities.SessionEntity;
import com.lingolearn.entities.VocabularySetEntity;
import com.lingolearn.enums.SessionState;
import com.lingolearn.enums.StudyMode;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

public class SessionRepository extends BaseRepository<SessionEntity, Long> {
    public SessionRepository() {
        super(SessionEntity.class);
    }

    public List<SessionEntity> findByVocabularySet(VocabularySetEntity set) {
        EntityManager em = dbManager.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT s FROM SessionEntity s WHERE s.vocabularySet = :set",
                            SessionEntity.class)
                    .setParameter("set", set)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<SessionEntity> findActiveSessionsBefore(LocalDateTime threshold) {
        EntityManager em = dbManager.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT s FROM SessionEntity s WHERE s.state = :state AND s.startTime < :threshold",
                            SessionEntity.class)
                    .setParameter("state", SessionState.IN_PROGRESS)
                    .setParameter("threshold", threshold)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<SessionEntity> findByStudyMode(StudyMode mode) {
        EntityManager em = dbManager.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT s FROM SessionEntity s WHERE s.mode = :mode",
                            SessionEntity.class)
                    .setParameter("mode", mode)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
