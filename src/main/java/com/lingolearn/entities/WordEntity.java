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

    // Getters and setters
    public WordEntity() {
    }

    public WordEntity(String original, String translation) {
        this.original = original;
        this.translation = translation;
    }

    public void updateDifficulty(Difficulty newDifficulty) {
        this.difficulty = newDifficulty;
        this.lastModifiedAt = LocalDateTime.now();
    }
}
