package com.lingolearn.vocabulary.word.infra;

import com.lingolearn.vocabulary.word.domain.Difficulty;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "words")
public class WordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String original;

    @Column(nullable = false)
    private String translation;

    @Column(name = "part_of_speech")
    private String partOfSpeech;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "word_examples",
            joinColumns = @JoinColumn(name = "word_id")
    )
    @Column(name = "example")
    private List<String> exampleSentences;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty;

    @Column(name = "last_reviewed")
    private LocalDateTime lastReviewed;

    @Column(name = "times_reviewed")
    private int timesReviewed;

    @Column(name = "correct_answers")
    private int correctAnswers;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public List<String> getExampleSentences() {
        return exampleSentences;
    }

    public void setExampleSentences(List<String> exampleSentences) {
        this.exampleSentences = exampleSentences;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public LocalDateTime getLastReviewed() {
        return lastReviewed;
    }

    public void setLastReviewed(LocalDateTime lastReviewed) {
        this.lastReviewed = lastReviewed;
    }

    public int getTimesReviewed() {
        return timesReviewed;
    }

    public void setTimesReviewed(int timesReviewed) {
        this.timesReviewed = timesReviewed;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
}
