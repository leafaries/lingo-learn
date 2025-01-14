package com.lingolearn.entities;

import com.lingolearn.enums.SessionType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "preferences")
public class PreferenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "keyboard_shortcuts")
    @MapKeyColumn(name = "action")
    @Column(name = "shortcut")
    private Map<String, String> keyboardShortcuts = new HashMap<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionType defaultSessionType = SessionType.FLASHCARD;

    @Column(nullable = false)
    private boolean soundEnabled = true;

    @Column(nullable = false)
    private boolean darkMode = false;

    @Column(nullable = false)
    private LocalDateTime lastModified = LocalDateTime.now();

    // Getters and setters
}
