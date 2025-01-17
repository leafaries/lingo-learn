package horizontallayers.infrastructure.jpaentities;

import horizontallayers.domain.enums.SessionType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a daily challenge in the language learning system.
 * Each day has one challenge that the user can complete to maintain
 * their learning streak and make consistent progress.
 */
@Entity
@Table(
        name = "daily_challenges",
        indexes = {
                @Index(name = "idx_daily_challenge_date", columnList = "date", unique = true)
        }
)
public class DailyChallengeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The study session associated with this challenge.
     */
    @OneToOne
    @JoinColumn(name = "session_id", nullable = false)
    private SessionEntity session;

    /**
     * The date of this challenge.
     */
    @Column(nullable = false, unique = true)
    private LocalDate date = LocalDate.now();

    /**
     * Whether the challenge has been completed successfully.
     */
    @Column(nullable = false)
    private boolean completed = false;

    /**
     * Target number of words for this challenge.
     */
    @Column(nullable = false)
    private int targetWordCount = 10;

    /**
     * Required accuracy percentage to complete the challenge.
     */
    @Column(nullable = false)
    private double requiredAccuracy = 70.0;

    /**
     * Time when the challenge was started.
     */
    @Column(nullable = false)
    private LocalDateTime startTime = LocalDateTime.now();

    /**
     * Time when the challenge was completed.
     */
    @Column
    private LocalDateTime completionTime;

    /**
     * Type of session for this challenge.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionType challengeType;

    /**
     * Default constructor required by JPA.
     */
    public DailyChallengeEntity() {
    }

    /**
     * Creates a new daily challenge with specified session and type.
     */
    public DailyChallengeEntity(SessionEntity session, SessionType challengeType) {
        this.session = session;
        this.challengeType = challengeType;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public SessionEntity getSession() {
        return session;
    }

    public void setSession(SessionEntity session) {
        this.session = session;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getTargetWordCount() {
        return targetWordCount;
    }

    public void setTargetWordCount(int targetWordCount) {
        this.targetWordCount = targetWordCount;
    }

    public double getRequiredAccuracy() {
        return requiredAccuracy;
    }

    public void setRequiredAccuracy(double requiredAccuracy) {
        this.requiredAccuracy = requiredAccuracy;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getCompletionTime() {
        return completionTime;
    }

    public SessionType getChallengeType() {
        return challengeType;
    }

    // Utility Methods

    /**
     * Checks if the challenge can be completed based on current progress.
     */
    public boolean canComplete() {
        if (session == null || completed) {
            return false;
        }

        return session.getTotalAnswers() >= targetWordCount &&
                session.getAccuracyPercentage() >= requiredAccuracy;
    }

    /**
     * Completes the challenge if requirements are met.
     *
     * @return true if challenge was completed successfully
     */
    public boolean tryComplete() {
        if (canComplete()) {
            this.completed = true;
            this.completionTime = LocalDateTime.now();
            return true;
        }
        return false;
    }

    /**
     * Checks if the challenge is still available for today.
     */
    public boolean isAvailableToday() {
        return date.equals(LocalDate.now()) && !completed;
    }

    /**
     * Gets current progress as a percentage.
     */
    public double getProgressPercentage() {
        if (session == null) return 0.0;

        double wordProgress = Math.min(1.0,
                (double) session.getTotalAnswers() / targetWordCount);
        double accuracyProgress = Math.min(1.0,
                session.getAccuracyPercentage() / requiredAccuracy);

        return (wordProgress + accuracyProgress) * 50.0; // Average of both criteria
    }

    /**
     * Checks if the challenge has expired (from previous day).
     */
    public boolean isExpired() {
        return date.isBefore(LocalDate.now());
    }

    // Object overrides

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DailyChallengeEntity)) return false;
        DailyChallengeEntity that = (DailyChallengeEntity) o;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "DailyChallengeEntity{" +
                "id=" + id +
                ", date=" + date +
                ", completed=" + completed +
                ", progress=" + String.format("%.1f%%", getProgressPercentage()) +
                ", type=" + challengeType +
                '}';
    }
}
