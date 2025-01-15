package com.lingolearn.entities;

import com.lingolearn.enums.SessionType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "preferences")
public class PreferenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(
            name = "keyboard_shortcuts",
            joinColumns = @JoinColumn(name = "preference_id")
    )
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

    // Default constructor
    public PreferenceEntity() {
    }

    // Getters
    public Long getId() {
        return id;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, String> getKeyboardShortcuts() {
        return new HashMap<>(keyboardShortcuts);  // Return a copy to prevent external modification
    }

    public void setKeyboardShortcuts(Map<String, String> keyboardShortcuts) {
        this.keyboardShortcuts = new HashMap<>(keyboardShortcuts);  // Store a copy
        this.lastModified = LocalDateTime.now();
    }

    public SessionType getDefaultSessionType() {
        return defaultSessionType;
    }

    public void setDefaultSessionType(SessionType defaultSessionType) {
        this.defaultSessionType = defaultSessionType;
        this.lastModified = LocalDateTime.now();
    }

    public boolean isSoundEnabled() {
        return soundEnabled;
    }

    public void setSoundEnabled(boolean soundEnabled) {
        this.soundEnabled = soundEnabled;
        this.lastModified = LocalDateTime.now();
    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
        this.lastModified = LocalDateTime.now();
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    // Utility methods
    public void addKeyboardShortcut(String action, String shortcut) {
        this.keyboardShortcuts.put(action, shortcut);
        this.lastModified = LocalDateTime.now();
    }

    public void removeKeyboardShortcut(String action) {
        this.keyboardShortcuts.remove(action);
        this.lastModified = LocalDateTime.now();
    }

    public void clearKeyboardShortcuts() {
        this.keyboardShortcuts.clear();
        this.lastModified = LocalDateTime.now();
    }

    // Override methods for proper entity behavior
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreferenceEntity that = (PreferenceEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PreferenceEntity{" +
                "id=" + id +
                ", defaultSessionType=" + defaultSessionType +
                ", soundEnabled=" + soundEnabled +
                ", darkMode=" + darkMode +
                ", lastModified=" + lastModified +
                ", keyboardShortcuts=" + keyboardShortcuts +
                '}';
    }
}
