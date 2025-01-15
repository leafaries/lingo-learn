package com.lingolearn.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 1024)
    private String description;

    @OneToMany(mappedBy = "category")
    private Set<VocabularySetEntity> vocabularySets = new HashSet<>();

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime lastModifiedAt = LocalDateTime.now();

    // Constructors
    public CategoryEntity() {
    }

    public CategoryEntity(String name) {
        this.name = name;
    }

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
    public void addVocabularySet(VocabularySetEntity set) {
        vocabularySets.add(set);
        set.setCategory(this);
        this.lastModifiedAt = LocalDateTime.now();
    }

    public void removeVocabularySet(VocabularySetEntity set) {
        vocabularySets.remove(set);
        set.setCategory(null);
        this.lastModifiedAt = LocalDateTime.now();
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
