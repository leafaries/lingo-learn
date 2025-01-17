package horizontallayers.infrastructure.jpaentities;

import com.lingolearn.vocabulary.set.infra.VocabularySetEntity;
import horizontallayers.domain.enums.Difficulty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This entity tracks learning progress through associated answers and can belong
 * to multiple vocabulary sets. It supports features like synonyms, antonyms,
 * and custom tags for enhanced learning experience.
 */
@Entity
@Table(
        name = "words",
        indexes = {
                @Index(name = "idx_word_original_translation", columnList = "original,translation"),
                @Index(name = "idx_word_difficulty", columnList = "difficulty")
        }
)
public class WordEntity {
    /**
     * Unique identifier for the word entity.
     * Automatically generated using database identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The word in its original language form.
     * Must not be null and must have length between 1 and 255 characters.
     */
    @NotNull(message = "Original word cannot be null")
    @Size(min = 1, max = 255, message = "Original word must be between 1 and 255 characters")
    @Column(nullable = false)
    private String original;

    /**
     * The word in its original language form.
     * Must not be null and must have length between 1 and 255 characters.
     */
    @NotNull(message = "Translation cannot be null")
    @Size(min = 1, max = 255, message = "Translation must be between 1 and 255 characters")
    @Column(nullable = false)
    private String translation;

    /**
     * Difficulty level of the word.
     * Defaults to MEDIUM if not specified.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty = Difficulty.MEDIUM;

    /**
     * Collection of user answers for this word across different study sessions.
     * Tracks learning progress and helps identify problematic words.
     */
    @OneToMany(
            mappedBy = "word",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<AnswerEntity> answers = new ArrayList<>();

    /**
     * Set of vocabulary sets this word belongs to.
     * A word can be part of multiple sets for flexible organization.
     */
    @ManyToMany(mappedBy = "words")
    private Set<VocabularySetEntity> vocabularySets = new HashSet<>();

    /**
     * Timestamp when the word was created.
     * Automatically set and never updated.
     */
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * Timestamp of the last modification to the word.
     * Automatically updated on each change.
     */
    @Column(nullable = false)
    private LocalDateTime lastModifiedAt = LocalDateTime.now();

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
        this.lastModifiedAt = LocalDateTime.now();
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
        this.lastModifiedAt = LocalDateTime.now();
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.lastModifiedAt = LocalDateTime.now();
    }

    public List<AnswerEntity> getAnswers() {
        return answers;
    }

    public Set<VocabularySetEntity> getVocabularySets() {
        return vocabularySets;
    }

    public void setVocabularySets(Set<VocabularySetEntity> vocabularySets) {
        this.vocabularySets = vocabularySets;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    /**
     * Updates the difficulty level of the word and records the modification
     * time.
     *
     * @param newDifficulty The new difficulty level to set
     */
    public void updateDifficulty(Difficulty newDifficulty) {
        this.difficulty = newDifficulty;
        this.lastModifiedAt = LocalDateTime.now();
    }

    /**
     * Adds a new answer to this word's history and establishes
     * the bidirectional relationship.
     *
     * @param answer The answer to add
     */
    public void addAnswer(AnswerEntity answer) {
        answers.add(answer);
        answer.setWord(this);
    }

    /**
     * Removes an answer from this word's history and breaks the bidirectional
     * relationship.
     *
     * @param answer The answer to remove
     */
    public void removeAnswer(AnswerEntity answer) {
        answers.remove(answer);
        answer.setWord(null);
    }

    /**
     * Adds this word to a vocabulary set and establishes the bidirectional relationship.
     *
     * @param set The vocabulary set to add this word to
     */
    public void addVocabularySet(VocabularySetEntity set) {
        vocabularySets.add(set);
        set.getWords().add(this);
    }

    /**
     * Removes this word from a vocabulary set and breaks the bidirectional relationship.
     *
     * @param set The vocabulary set to remove this word from
     */
    public void removeVocabularySet(VocabularySetEntity set) {
        vocabularySets.remove(set);
        set.getWords().remove(this);
    }

    // Object overrides

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WordEntity)) return false;
        WordEntity that = (WordEntity) o;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "WordEntity{" +
                "id=" + id +
                ", original='" + original + '\'' +
                ", translation='" + translation + '\'' +
                ", difficulty=" + difficulty +
                '}';
    }
}
