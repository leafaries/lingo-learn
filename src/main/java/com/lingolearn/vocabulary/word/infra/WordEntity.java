package com.lingolearn.vocabulary.word.infra;

import com.lingolearn.vocabulary.set.infra.VocabularySetEntity;
import com.lingolearn.vocabulary.word.domain.Difficulty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(
        name = "words",
        indexes = {
                @Index(name = "idx_word_original_translation", columnList = "original,translation"),
                @Index(name = "idx_word_difficulty", columnList = "difficulty")
        }
)
public class WordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Original word cannot be null")
    @Size(min = 1, max = 255, message = "Original word must be between 1 and 255 characters")
    @Column(nullable = false)
    private String original;

    @NotNull(message = "Translation cannot be null")
    @Size(min = 1, max = 255, message = "Translation must be between 1 and 255 characters")
    @Column(nullable = false)
    private String translation;

    @Size(max = 50)
    @Column(name = "part_of_speech")
    private String partOfSpeech;

    @ElementCollection
    @CollectionTable(name = "word_examples", joinColumns = @JoinColumn(name = "word_id"))
    private List<String> exampleSentences = new ArrayList<>();

    @ManyToMany(mappedBy = "words")
    private Set<VocabularySetEntity> vocabularySets = new HashSet<>();

    @Column(nullable = false)
    private LocalDateTime lastReviedAt = LocalDateTime.now();

    @Column(name = "times_revied")
    private int timesReviewed = 0;

    @Column(name = "correct_answers")
    private int correctAnswers = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty = Difficulty.MEDIUM;

    // Constructors

    /**
     * Default constructor required by JPA.
     */
    public WordEntity() {
    }

    /**
     * Creates a new word with the specified original text and translation.
     *
     * @param original The word in its original language
     * @param translation The translation of the word
     */
    public WordEntity(String original, String translation) {
        this.original = original;
        this.translation = translation;
    }

    // Getters and Setters

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public List<String> getExampleSentences() {
        return exampleSentences;
    }

    public void setExampleSentences(List<String> exampleSentences) {
        this.exampleSentences = exampleSentences;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLastReviedAt() {
        return lastReviedAt;
    }

    public void setLastReviedAt(LocalDateTime lastReviedAt) {
        this.lastReviedAt = lastReviedAt;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public int getTimesReviewed() {
        return timesReviewed;
    }

    public void setTimesReviewed(int timesReviewed) {
        this.timesReviewed = timesReviewed;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public Set<VocabularySetEntity> getVocabularySets() {
        return vocabularySets;
    }

    public void setVocabularySets(Set<VocabularySetEntity> vocabularySets) {
        this.vocabularySets = vocabularySets;
    }
}
