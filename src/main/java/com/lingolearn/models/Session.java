package com.lingolearn.models;

import com.lingolearn.enums.SessionState;
import com.lingolearn.enums.SessionType;
import com.lingolearn.enums.StudyMode;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Session {
    private final UUID id;
    private final LearningItem learningItem;
    private final StudyMode mode;
    private final SessionType type;
    private final Instant startTime;
    private final List<Answer> answers;
    private Instant endTime;
    private SessionState state;

    public Session(LearningItem learningItem, StudyMode mode, SessionType type) {
        this.id = UUID.randomUUID();
        this.learningItem = learningItem;
        this.mode = mode;
        this.type = type;
        this.startTime = Instant.now();
        this.answers = new ArrayList<>();
        this.state = SessionState.IN_PROGRESS;
    }

    public UUID getId() {
        return id;
    }

    public LearningItem getLearningItem() {
        return learningItem;
    }

    public StudyMode getMode() {
        return mode;
    }

    public SessionType getType() {
        return type;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public List<Answer> getAnswers() {
        return new ArrayList<>(answers);
    }

    public SessionState getState() {
        return state;
    }

    public Set<UUID> getWordsToStudy() {
        return learningItem.getAllWordIds();
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public void complete() {
        this.state = SessionState.COMPLETED;
        this.endTime = Instant.now();
    }

    public void abandon() {
        this.state = SessionState.ABANDONED;
        this.endTime = Instant.now();
    }
}
