package horizontallayers.infrastructure.jpaentities;

import com.lingolearn.vocabulary.set.infra.VocabularySetEntity;
import horizontallayers.domain.enums.SessionState;
import horizontallayers.domain.enums.SessionType;
import horizontallayers.domain.enums.StudyMode;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a study session in the language learning system.
 * Tracks user's interactions with vocabulary sets during learning,
 * including performance metrics and progress.
 */
@Entity
@Table(
        name = "study_sessions",
        indexes = {
                @Index(name = "idx_session_start_time", columnList = "startTime"),
                @Index(name = "idx_session_vocabulary_set", columnList = "vocabulary_set_id"),
                @Index(name = "idx_session_state", columnList = "state")
        }
)
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The vocabulary set being studied in this session.
     */
    @NotNull
    @ManyToOne
    @JoinColumn(name = "vocabulary_set_id", nullable = false)
    private VocabularySetEntity vocabularySet;

    /**
     * The mode of study for this session.
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StudyMode mode;

    /**
     * The type of interaction used in this session.
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionType type;

    /**
     * Current state of the session.
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionState state = SessionState.IN_PROGRESS;

    /**
     * Start time of the session.
     */
    @NotNull
    @Column(nullable = false)
    private LocalDateTime startTime = LocalDateTime.now();

    /**
     * End time of the session. Set when session is completed or abandoned.
     */
    @Column
    private LocalDateTime endTime;

    /**
     * List of all answers provided during this session
     */
    @OneToMany(
            mappedBy = "session",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @OrderBy("timestamp ASC")
    private List<AnswerEntity> answers = new ArrayList<>();

    /**
     * Number of correct answers in this session
     */
    @Column(nullable = false)
    private int correctAnswers = 0;

    /**
     * Total number of answers provided in this session.
     */
    @Column
    private int totalAnswers = 0;

    /**
     * Current streak of correct answers.
     */
    @Column(nullable = false)
    private int currentStreak = 0;

    /**
     * Best streak achieved in this session.
     */
    @Column(nullable = false)
    private int bestStreak = 0;

    // Constructors

    /**
     * Default constructor required by JPA.
     */
    public SessionEntity() {
    }

    /**
     * Creates a new session with specified vocabulary set and mode.
     */
    public SessionEntity(VocabularySetEntity vocabularySet, StudyMode mode, SessionType type) {
        this.vocabularySet = vocabularySet;
        this.mode = mode;
        this.type = type;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public VocabularySetEntity getVocabularySet() {
        return vocabularySet;
    }

    public void setVocabularySet(VocabularySetEntity vocabularySet) {
        this.vocabularySet = vocabularySet;
    }

    public StudyMode getMode() {
        return mode;
    }

    public SessionType getType() {
        return type;
    }

    public SessionState getState() {
        return state;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<AnswerEntity> getAnswers() {
        return answers;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getTotalAnswers() {
        return totalAnswers;
    }

    public int getBestStreak() {
        return bestStreak;
    }

    public int getCurrentStreak() {
        return currentStreak;
    }

    // Utility methods

    /**
     * Records a new answer in the session and updates statistics.
     */
    public void addAnswer(AnswerEntity answer) {
        answers.add(answer);
        answer.setSession(this);
        totalAnswers++;

        if (answer.isCorrect()) {
            correctAnswers++;
            currentStreak++;
            bestStreak = Math.max(bestStreak, currentStreak);
        } else {
            currentStreak = 0;
        }
    }

    /**
     * Marks the session as completed and records the end time.
     */
    public void complete() {
        this.state = SessionState.COMPLETED;
        this.endTime = LocalDateTime.now();
    }

    /**
     * Marks the session as abandoned and records the end time.
     */
    public void abandon() {
        this.state = SessionState.ABANDONED;
        this.endTime = LocalDateTime.now();
    }

    /**
     * Calculates the accuracy percentage for this session.
     */
    public double getAccuracyPercentage() {
        if (totalAnswers == 0) return 0.0;
        return (double) correctAnswers / totalAnswers * 100;
    }

    /**
     * Calculates the duration of the session.
     */
    public Duration getDuration() {
        LocalDateTime end = endTime != null ? endTime : LocalDateTime.now();
        return Duration.between(startTime, end);
    }

    /**
     * Checks if the session is active.
     */
    public boolean isActive() {
        return state == SessionState.IN_PROGRESS;
    }

    // Object overrides

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SessionEntity)) return false;
        SessionEntity that = (SessionEntity) o;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "SessionEntity{" +
                "id=" + id +
                ", mode=" + mode +
                ", type=" + type +
                ", state=" + state +
                ", startTime=" + startTime +
                ", accuracy=" + String.format("%.2f", getAccuracyPercentage()) +
                ", answers=" + totalAnswers +
                '}';
    }
}
