package repos;

import com.lingolearn.BaseRepository;
import horizontallayers.infrastructure.jpaentities.StatisticsEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class StatisticsRepository extends BaseRepository<StatisticsEntity, Long> {

    public StatisticsRepository() {
        super(StatisticsEntity.class);
    }

    /**
     * Finds statistics for a specific date
     */
    public Optional<StatisticsEntity> findByDate(LocalDate date) {
        try (EntityManager em = dbManager.createEntityManager()) {
            TypedQuery<StatisticsEntity> query = em.createQuery(
                    "SELECT s FROM StatisticsEntity s WHERE s.date = :date",
                    StatisticsEntity.class
            );
            query.setParameter("date", date);
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    /**
     * Gets statistics for a date range
     */
    public List<StatisticsEntity> findBetweenDates(LocalDate startDate, LocalDate endDate) {
        try (EntityManager em = dbManager.createEntityManager()) {
            return em.createQuery(
                            "SELECT s FROM StatisticsEntity s WHERE s.date BETWEEN :startDate AND :endDate ORDER BY s.date",
                            StatisticsEntity.class
                    )
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();
        }
    }

    /**
     * Gets the current streak (consecutive days with completed challenges)
     */
    public int getCurrentStreak() {
        try (EntityManager em = dbManager.createEntityManager()) {
            TypedQuery<StatisticsEntity> query = em.createQuery(
                    "SELECT s FROM StatisticsEntity s " +
                            "WHERE s.dailyChallengesCompleted > 0 " +
                            "ORDER BY s.date DESC",
                    StatisticsEntity.class
            );
            query.setMaxResults(1);

            StatisticsEntity latest = query.getSingleResult();
            return latest.getStreakDays();
        } catch (NoResultException e) {
            return 0;
        }
    }

    /**
     * Gets the longest streak ever achieved
     */
    public int getLongestStreak() {
        try (EntityManager em = dbManager.createEntityManager()) {
            return em.createQuery(
                    "SELECT MAX(s.streakDays) FROM StatisticsEntity s",
                    Integer.class
            ).getSingleResult();
        } catch (NoResultException e) {
            return 0;
        }
    }

    /**
     * Gets total words learned in the last n days
     */
    public int getTotalWordsLearnedInLastDays(int days) {
        try (EntityManager em = dbManager.createEntityManager()) {
            LocalDate cutoffDate = LocalDate.now().minusDays(days);
            return em.createQuery(
                            "SELECT SUM(s.totalWordsLearned) FROM StatisticsEntity s " +
                                    "WHERE s.date >= :cutoffDate",
                            Long.class
                    )
                    .setParameter("cutoffDate", cutoffDate)
                    .getSingleResult().intValue();
        } catch (NoResultException e) {
            return 0;
        }
    }

    /**
     * Gets average accuracy (correct answers percentage) for a date range
     */
    public double getAverageAccuracy(LocalDate startDate, LocalDate endDate) {
        try (EntityManager em = dbManager.createEntityManager()) {
            Object[] results = (Object[]) em.createQuery(
                            "SELECT SUM(s.correctAnswers), SUM(s.totalAnswers) " +
                                    "FROM StatisticsEntity s " +
                                    "WHERE s.date BETWEEN :startDate AND :endDate"
                    )
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getSingleResult();

            Long correctAnswers = (Long) results[0];
            Long totalAnswers = (Long) results[1];

            if (totalAnswers == null || totalAnswers == 0) {
                return 0.0;
            }

            return (double) correctAnswers / totalAnswers * 100.0;
        }
    }

    /**
     * Gets statistics summary for monthly report
     */
    public List<StatisticsEntity> getMonthlyStatistics(int year, int month) {
        try (EntityManager em = dbManager.createEntityManager()) {
            LocalDate startDate = LocalDate.of(year, month, 1);
            LocalDate endDate = startDate.plusMonths(1).minusDays(1);

            return em.createQuery(
                            "SELECT s FROM StatisticsEntity s " +
                                    "WHERE s.date BETWEEN :startDate AND :endDate " +
                                    "ORDER BY s.date",
                            StatisticsEntity.class
                    )
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();
        }
    }

    /**
     * Deletes all statistics before a given date
     */
    public void deleteStatisticsBefore(LocalDate date) {
        EntityManager em = dbManager.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery(
                            "DELETE FROM StatisticsEntity s WHERE s.date < :date"
                    )
                    .setParameter("date", date)
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Failed to delete old statistics", e);
        } finally {
            em.close();
        }
    }
}
