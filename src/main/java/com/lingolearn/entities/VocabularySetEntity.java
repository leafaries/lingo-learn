package com.lingolearn.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a collection of words that are grouped together for learning purposes.
 * A vocabulary set can contain multiple words and can be associated with a category.
 * Each set tracks its creation and modification times.
 */
@Entity
@Table(
        name = "vocabulary_sets",
        indexes = {
                @Index(name = "idx_vocabulary_set_name", columnList = "name"),
                @Index(name = "idx_vocabulary_set_category", columnList = "category_id")
        }
)
public class VocabularySetEntity {
    /**
     * Unique identifier for the vocabulary set.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the vocabulary set.
     * Must not be null and must be between 1 and 255 characters.
     */
    @NotNull(message = "Set name cannot be null")
    @Size(min = 1, max = 255, message = "Set name must be between 1 and 255 characters")
    @Column(nullable = false)
    private String name;

    /**
     * Optional description of the vocabulary set.
     */
    @Size(max = 1024, message = "Description cannot exceed 1024 characters")
    @Column(length = 1024)
    private String description;

    /**
     * Category to which this vocabulary set belongs.
     * Optional - a set doesn't need to belong to a category.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    /**
     * Collection of words contained in this vocabulary set.
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "vocabulary_set_words",
            joinColumns = @JoinColumn(name = "set_id"),
            inverseJoinColumns = @JoinColumn(name = "word_id")
    )
    private Set<WordEntity> words = new HashSet<>();

    /**
     * Collection of study sessions associated with this vocabulary set.
     */
    @OneToMany(mappedBy = "vocabularySet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SessionEntity> sessions = new HashSet<>();

    /**
     * Creation timestamp, automatically set when the set is first created.
     */
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * Last modification timestamp, automatically updated when the set is modified.
     */
    @Column(nullable = false)
    private LocalDateTime lastModifiedAt = LocalDateTime.now();

    // Constructors

    /**
     * Default constructor required by JPA.
     */
    public VocabularySetEntity() {
    }

    /**
     * Creates a new vocabulary set with the specified name and description.
     *
     * @param name The name of the vocabulary set
     * @param description The description of the vocabulary set
     */

    public VocabularySetEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.lastModifiedAt = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.lastModifiedAt = LocalDateTime.now();
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
        this.lastModifiedAt = LocalDateTime.now();
    }

    public Set<WordEntity> getWords() {
        return words;
    }

    public void setWords(Set<WordEntity> words) {
        this.words = words;
        this.lastModifiedAt = LocalDateTime.now();
    }

    public Set<SessionEntity> getSessions() {
        return sessions;
    }

    public void setSessions(Set<SessionEntity> sessions) {
        this.sessions = sessions;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    // Utility methods

    /**
     * Adds a word to this vocabulary set and establishes the bidirectional
     * relationship.
     *
     * @param word The word to add to the set
     */
    public void addWord(WordEntity word) {
        words.add(word);
        word.getVocabularySets().add(this);
        this.lastModifiedAt = LocalDateTime.now();
    }

    /**
     * Removes a word from this vocabulary set and breaks the bidirectional relationship.
     *
     * @param word The word to remove from the set
     */
    public void removeWord(WordEntity word) {
        words.remove(word);
        word.getVocabularySets().remove(this);
        this.lastModifiedAt = LocalDateTime.now();
    }

    /**
     * Adds a study session to this vocabulary set.
     *
     * @param session The session to add
     */
    public void addSession(SessionEntity session) {
        sessions.add(session);
        session.setVocabularySet(this);
    }

    /**
     * Removes a study session from this vocabulary set.
     *
     * @param session The session to remove
     */
    public void removeSession(SessionEntity session) {
        sessions.remove(session);
        session.setVocabularySet(null);
    }

    /**
     * Calculates the success rate for this vocabulary set based on all sessions.
     *
     * @return The percentage of correct answers across all sessions
     */
    public double getSuccessRate() {
        if (sessions.isEmpty()) return 0.0;

        int totalCorrect = sessions.stream()
                .mapToInt(SessionEntity::getCorrectAnswers)
                .sum();
        int totalAnswers = sessions.stream()
                .mapToInt(SessionEntity::getTotalAnswers)
                .sum();

        return totalAnswers == 0 ? 0.0 : (double) totalCorrect / totalAnswers * 100;
    }

    // Object overrides

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VocabularySetEntity)) return false;
        VocabularySetEntity that = (VocabularySetEntity) o;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "VocabularySetEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + (category != null ? category.getName() : "null") +
                ", wordsCount=" + words.size() +
                '}';
    }
}
