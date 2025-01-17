package repos;

import com.lingolearn.BaseRepository;
import com.lingolearn.vocabulary.set.infra.JpaVocabularySetRepository;
import horizontallayers.domain.enums.SessionState;
import horizontallayers.domain.enums.StudyMode;
import horizontallayers.infrastructure.jpaentities.SessionEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class SessionRepository extends BaseRepository<SessionEntity, Long> {
    private final JpaVocabularySetRepository vocabularySetRepo;
    private EntityTransaction currentTransaction;

    public SessionRepository(JpaVocabularySetRepository vocabularySetRepo) {
        super(SessionEntity.class);
        this.vocabularySetRepo = vocabularySetRepo;
    }

    /**
     * Finds all sessions for a vocabulary set identified by its name.
     */
    public List<SessionEntity> findByVocabularySetName(String setName) {
        EntityManager em = dbManager.createEntityManager();
        try {
            TypedQuery<SessionEntity> query = em.createQuery(
                    "SELECT s FROM SessionEntity s " +
                            "JOIN s.vocabularySet v " +
                            "WHERE v.name = :setName",
                    SessionEntity.class
            );
            query.setParameter("setName", setName);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Finds active (IN_PROGRESS) sessions that started before the given time.
     */
    public List<SessionEntity> findActiveSessionsBefore(LocalDateTime threshold) {
        EntityManager em = dbManager.createEntityManager();
        try {
            TypedQuery<SessionEntity> query = em.createQuery(
                    "SELECT s FROM SessionEntity s " +
                            "WHERE s.state = :state " +
                            "AND s.startTime < :threshold",
                    SessionEntity.class
            );
            query.setParameter("state", SessionState.IN_PROGRESS);
            query.setParameter("threshold", threshold);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Finds all sessions of a specific study mode.
     */
    public List<SessionEntity> findByStudyMode(StudyMode mode) {
        EntityManager em = dbManager.createEntityManager();
        try {
            TypedQuery<SessionEntity> query = em.createQuery(
                    "SELECT s FROM SessionEntity s WHERE s.mode = :mode",
                    SessionEntity.class
            );
            query.setParameter("mode", mode);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Finds all sessions between two dates.
     */
    public List<SessionEntity> findBetweenDates(LocalDateTime start, LocalDateTime end) {
        EntityManager em = dbManager.createEntityManager();
        try {
            TypedQuery<SessionEntity> query = em.createQuery(
                    "SELECT s FROM SessionEntity s " +
                            "WHERE s.startTime >= :start " +
                            "AND s.startTime <= :end " +
                            "ORDER BY s.startTime DESC",
                    SessionEntity.class
            );
            query.setParameter("start", start);
            query.setParameter("end", end);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Finds completed sessions with accuracy above the specified threshold.
     */
    public List<SessionEntity> findCompletedSessionsWithHighAccuracy(double accuracyThreshold) {
        EntityManager em = dbManager.createEntityManager();
        try {
            TypedQuery<SessionEntity> query = em.createQuery(
                    "SELECT s FROM SessionEntity s " +
                            "WHERE s.state = :state " +
                            "AND CAST(s.correctAnswers AS double) / " +
                            "CASE WHEN s.totalAnswers = 0 THEN 1 ELSE s.totalAnswers END >= :threshold",
                    SessionEntity.class
            );
            query.setParameter("state", SessionState.COMPLETED);
            query.setParameter("threshold", accuracyThreshold);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Finds the latest session for a vocabulary set.
     */
    public Optional<SessionEntity> findLatestByVocabularySetName(String setName) {
        EntityManager em = dbManager.createEntityManager();
        try {
            TypedQuery<SessionEntity> query = em.createQuery(
                    "SELECT s FROM SessionEntity s " +
                            "JOIN s.vocabularySet v " +
                            "WHERE v.name = :setName " +
                            "ORDER BY s.startTime DESC",
                    SessionEntity.class
            );
            query.setParameter("setName", setName);
            query.setMaxResults(1);
            List<SessionEntity> results = query.getResultList();
            return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
        } finally {
            em.close();
        }
    }

    public void beginTransaction() {
        EntityManager em = dbManager.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        this.currentTransaction = tx;
    }

    public void commitTransaction() {
        if (currentTransaction != null && currentTransaction.isActive()) {
            currentTransaction.commit();
            currentTransaction = null;
        }
    }

    public void rollbackTransaction() {
        if (currentTransaction != null && currentTransaction.isActive()) {
            currentTransaction.rollback();
            currentTransaction = null;
        }
    }

    @Override
    public SessionEntity save(SessionEntity entity) {
        if (currentTransaction != null && currentTransaction.isActive()) {
            EntityManager em = dbManager.createEntityManager();
            try {
                return em.merge(entity);
            } finally {
                em.close();
            }
        } else {
            return super.save(entity);
        }
    }
}
