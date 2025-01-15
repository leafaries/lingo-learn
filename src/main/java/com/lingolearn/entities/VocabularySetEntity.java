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

    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToMany
    @JoinTable(
            name = "vocabulary_set_words",
            joinColumns = @JoinColumn(name = "set_id"),
            inverseJoinColumns = @JoinColumn(name = "word_id")
    )
    private Set<WordEntity> words = new HashSet<>();

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime lastModifiedAt = LocalDateTime.now();

    // Getters and setters
    public VocabularySetEntity() {
    }

    public VocabularySetEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

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

    public CategoryEntity getCategory() {
        return category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public String getName() {
        return name;
    }

    public Set<WordEntity> getWords() {
        return words;
    }
}
