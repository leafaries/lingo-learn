package com.lingolearn.entities;

import com.lingolearn.enums.SessionState;
import com.lingolearn.enums.SessionType;
import com.lingolearn.enums.StudyMode;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "study_sessions")
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vocabulary_set_id", nullable = false)
    private VocabularySetEntity vocabularySet;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StudyMode mode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionState state = SessionState.IN_PROGRESS;

    @Column(nullable = false)
    private LocalDateTime startTime = LocalDateTime.now();

    private LocalDateTime endTime;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<AnswerEntity> answers = new ArrayList<>();

    @Column(nullable = false)
    private boolean completed = false;

    @Column
    private int correctAnswers = 0;

    @Column
    private int totalAnswers = 0;

    // Constructors
    public SessionEntity() {
    }

    public SessionEntity(VocabularySetEntity vocabularySet, StudyMode mode, SessionType type) {
        this.vocabularySet = vocabularySet;
        this.mode = mode;
        this.type = type;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setMode(StudyMode mode) {
        this.mode = mode;
    }

    public SessionType getType() {
        return type;
    }

    public void setType(SessionType type) {
        this.type = type;
    }

    public SessionState getState() {
        return state;
    }

    public void setState(SessionState state) {
        this.state = state;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
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

    public void setAnswers(List<AnswerEntity> answers) {
        this.answers = answers;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getTotalAnswers() {
        return totalAnswers;
    }

    public void setTotalAnswers(int totalAnswers) {
        this.totalAnswers = totalAnswers;
    }

    // Utility methods
    public void addAnswer(AnswerEntity answer) {
        answers.add(answer);
        answer.setSession(this);
        totalAnswers++;
        if (answer.isCorrect()) {
            correctAnswers++;
        }
    }

    public void removeAnswer(AnswerEntity answer) {
        answers.remove(answer);
        answer.setSession(null);
        totalAnswers--;
        if (answer.isCorrect()) {
            correctAnswers--;
        }
    }

    public void complete() {
        this.state = SessionState.COMPLETED;
        this.endTime = LocalDateTime.now();
        this.completed = true;
    }

    public void abandon() {
        this.state = SessionState.ABANDONED;
        this.endTime = LocalDateTime.now();
    }

    public double getAccuracy() {
        if (totalAnswers == 0) return 0.0;
        return (double) correctAnswers / totalAnswers;
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
                ", completed=" + completed +
                ", accuracy=" + String.format("%.2f", getAccuracy()) +
                '}';
    }
}
