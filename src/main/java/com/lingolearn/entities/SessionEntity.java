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

    // Getters and setters
    public SessionEntity() {
    }

    public SessionEntity(VocabularySetEntity vocabularySet, StudyMode mode, SessionType type) {
        this.vocabularySet = vocabularySet;
        this.mode = mode;
        this.type = type;
    }

    public void addAnswer(AnswerEntity answer) {
        answers.add(answer);
        answer.setSession(this);
    }

    public void complete() {
        this.state = SessionState.COMPLETED;
        this.endTime = LocalDateTime.now();
    }

    public void abandon() {
        this.state = SessionState.ABANDONED;
        this.endTime = LocalDateTime.now();
    }
}
