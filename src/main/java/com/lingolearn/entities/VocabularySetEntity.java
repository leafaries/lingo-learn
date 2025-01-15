package com.lingolearn.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vocabulary_sets")
public class VocabularySetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1024)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "vocabulary_set_words",
            joinColumns = @JoinColumn(name = "set_id"),
            inverseJoinColumns = @JoinColumn(name = "word_id")
    )
    private Set<WordEntity> words = new HashSet<>();

    @OneToMany(mappedBy = "vocabularySet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SessionEntity> sessions = new HashSet<>();

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime lastModifiedAt = LocalDateTime.now();

    // Constructors
    public VocabularySetEntity() {
    }

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
    public void addWord(WordEntity word) {
        words.add(word);
        word.getVocabularySets().add(this);
        this.lastModifiedAt = LocalDateTime.now();
    }

    public void removeWord(WordEntity word) {
        words.remove(word);
        word.getVocabularySets().remove(this);
        this.lastModifiedAt = LocalDateTime.now();
    }

    public void addSession(SessionEntity session) {
        sessions.add(session);
        session.setVocabularySet(this);
    }

    public void removeSession(SessionEntity session) {
        sessions.remove(session);
        session.setVocabularySet(null);
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
