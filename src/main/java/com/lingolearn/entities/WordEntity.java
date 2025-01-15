package com.lingolearn.entities;

import com.lingolearn.enums.Difficulty;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty = Difficulty.MEDIUM;

    @OneToMany(mappedBy = "word", cascade = CascadeType.ALL)
    private List<AnswerEntity> answers = new ArrayList<>();

    @ManyToMany(mappedBy = "words")
    private Set<VocabularySetEntity> vocabularySets = new HashSet<>();

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime lastModifiedAt = LocalDateTime.now();

    // Constructors
    public WordEntity() {
    }

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

    public void setAnswers(List<AnswerEntity> answers) {
        this.answers = answers;
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

    // Utility methods
    public void updateDifficulty(Difficulty newDifficulty) {
        this.difficulty = newDifficulty;
        this.lastModifiedAt = LocalDateTime.now();
    }

    public void addAnswer(AnswerEntity answer) {
        answers.add(answer);
        answer.setWord(this);
    }

    public void removeAnswer(AnswerEntity answer) {
        answers.remove(answer);
        answer.setWord(null);
    }

    public void addVocabularySet(VocabularySetEntity set) {
        vocabularySets.add(set);
        set.getWords().add(this);
    }

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
