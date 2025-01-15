package com.lingolearn.entities;

import com.lingolearn.enums.SessionType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Stores user preferences for the language learning application.
 * Since this is a single-user application, there will typically be only one
 * active preference record at a time.
 */
@Entity
@Table(name = "preferences")
public class PreferenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Keyboard shortcuts mapping. Keys are action names, values are shortcut
     * combinations.
     */
    @ElementCollection
    @CollectionTable(
            name = "keyboard_shortcuts",
            joinColumns = @JoinColumn(name = "preference_id")
    )
    @MapKeyColumn(name = "action")
    @Column(name = "shortcut")
    private Map<String, String> keyboardShortcuts = new HashMap<>();

    /**
     * Default session type for study sessions.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionType defaultSessionType = SessionType.FLASHCARD;

    /**
     * Whether sound feedback is enabled.
     */
    @Column(nullable = false)
    private boolean soundEnabled = true;

    /**
     * Whether dark mode is enabled.
     */
    @Column(nullable = false)
    private boolean darkMode = false;

    /**
     * Default number of words per study session.
     */
    @Column(nullable = false)
    private int defaultWordsPerSession = 20;

    /**
     * Time limit in minutes for study sessions. 0 means no limit.
     */
    @Column(nullable = false)
    private int sessionTimeLimit = 0;

    @Column(nullable = false)
    private LocalDateTime lastModified = LocalDateTime.now();

    // Constructors

    /**
     * Default constructor required by JPA.
     */
    public PreferenceEntity() {
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public Map<String, String> getKeyboardShortcuts() {
        return new HashMap<>(keyboardShortcuts); // Return a copy to prevent external modification
    }

    public void setKeyboardShortcuts(Map<String, String> keyboardShortcuts) {
        this.keyboardShortcuts = new HashMap<>(keyboardShortcuts);
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

    public int getDefaultWordsPerSession() {
        return defaultWordsPerSession;
    }

    public void setDefaultWordsPerSession(int defaultWordsPerSession) {
        this.defaultWordsPerSession = defaultWordsPerSession;
        this.lastModified = LocalDateTime.now();
    }

    public int getSessionTimeLimit() {
        return sessionTimeLimit;
    }

    public void setSessionTimeLimit(int sessionTimeLimit) {
        this.sessionTimeLimit = sessionTimeLimit;
        this.lastModified = LocalDateTime.now();
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    // Utility methods

    /**
     * Adds or updates a keyboard shortcut.
     */
    public void addKeyboardShortcut(String action, String shortcut) {
        this.keyboardShortcuts.put(action, shortcut);
        this.lastModified = LocalDateTime.now();
    }

    /**
     * Removes a keyboard shortcut.
     */
    public void removeKeyboardShortcut(String action) {
        this.keyboardShortcuts.remove(action);
        this.lastModified = LocalDateTime.now();
    }

    /**
     * Clears all keyboard shortcuts.
     */
    public void clearKeyboardShortcuts() {
        this.keyboardShortcuts.clear();
        this.lastModified = LocalDateTime.now();
    }

    /**
     * Resets all preferences to default values.
     */
    public void resetToDefaults() {
        this.defaultSessionType = SessionType.FLASHCARD;
        this.soundEnabled = true;
        this.darkMode = false;
        this.defaultWordsPerSession = 20;
        this.sessionTimeLimit = 0;
        this.keyboardShortcuts.clear();
        this.lastModified = LocalDateTime.now();
    }

    // Override overrides

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
                ", defaultWordsPerSession=" + defaultWordsPerSession +
                ", sessionTimeLimit=" + sessionTimeLimit +
                ", shortcutsCount=" + keyboardShortcuts.size() +
                '}';
    }
}
