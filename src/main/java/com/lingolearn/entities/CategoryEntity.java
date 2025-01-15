package com.lingolearn.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a category for organizing vocabulary sets.
 * Categories help group related vocabulary sets together (e.g., "Travel",
 * "Business", "Food"). Categories are unique by name and can contain multiple
 * vocabulary sets.
 */
@Entity
@Table(
        name = "categories",
        indexes = {
                @Index(name = "idx_category_name", columnList = "name", unique = true)
        }
)
public class CategoryEntity {
    /**
     * Unique identifier for the category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the category. Must be unique.
     */
    @NotNull(message = "Category name cannot be null")
    @Size(min = 1, max = 255, message = "Category name must be between 1 and 255 characters")
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Optional description of the category.
     */
    @Size(max = 1024, message = "Description cannot exceed 1024 characters")
    @Column(length = 1024)
    private String description;

    /**
     * Collection of vocabulary sets belonging to this category.
     */
    @OneToMany(
            mappedBy = "category",
            fetch = FetchType.LAZY
    )
    private Set<VocabularySetEntity> vocabularySets = new HashSet<>();

    /**
     * Creation timestamp.
     */
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * Last modification timestamp.
     */
    @Column(nullable = false)
    private LocalDateTime lastModifiedAt = LocalDateTime.now();

    // Constructors

    /**
     * Default constructor required by JPA.
     */
    public CategoryEntity() {
    }

    /**
     * Creates a new category with the specified name.
     *
     * @param name The name of the category
     */
    public CategoryEntity(String name) {
        this.name = name;
    }

    /**
     * Creates a new category with the specified name.
     *
     * @param name The name of the category
     */
    public CategoryEntity(String name, String description) {
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

    public Set<VocabularySetEntity> getVocabularySets() {
        return vocabularySets;
    }

    public void setVocabularySets(Set<VocabularySetEntity> vocabularySets) {
        this.vocabularySets = vocabularySets;
        this.lastModifiedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    // Utility methods

    /**
     * Adds a vocabulary set to this category and establishes the bidirectional
     * relationship.
     *
     * @param set The vocabulary set to add
     */
    public void addVocabularySet(VocabularySetEntity set) {
        vocabularySets.add(set);
        set.setCategory(this);
        this.lastModifiedAt = LocalDateTime.now();
    }

    /**
     * Removes a vocabulary set from this category and breaks the bidirectional
     * relationship.
     *
     * @param set The vocabulary set to remove
     */
    public void removeVocabularySet(VocabularySetEntity set) {
        vocabularySets.remove(set);
        set.setCategory(null);
        this.lastModifiedAt = LocalDateTime.now();
    }

    /**
     * Returns the total number of words across all vocabulary sets in this category.
     *
     * @return The total word count
     */
    public int getTotalWordCount() {
        return vocabularySets.stream()
                .mapToInt(set -> set.getWords().size())
                .sum();
    }

    // Object overrides

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryEntity)) return false;
        CategoryEntity that = (CategoryEntity) o;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", vocabularySetsCount=" + vocabularySets.size() +
                '}';
    }
}
