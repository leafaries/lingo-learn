# list_of_design_patterns

## 1. Facade Pattern

```java
// Main facade hiding complex subsystem operations
public interface LingoLearnFacade {
    // All system operations exposed through this single interface
}
```

## 2. Composite Pattern

```java
// Allows building vocabulary set hierarchies
public record VocabularySet(
                UUID id,
                String name,
                String description,
                Set<UUID> wordIds,
                Set<UUID> subSetIds, // Composite pattern - sets can contain other sets
                UUID categoryId
        ) {
}
```

## 3. Strategy Pattern

```java
// Different learning strategies
public sealed interface LearningStrategy
        permits FlashcardStrategy, TranslationChoiceStrategy, ManualTranslationStrategy {
    Word getNextWord(UUID sessionId);
    boolean evaluateAnswer(UUID sessionId, String answer);
}

public final class FlashcardStrategy implements LearningStrategy {
    // Implementation for flashcard learning
}
```

## 4. Observer Pattern

```java
// Progress tracking
public sealed interface ProgressListener
        permits StatisticsManager, AchievementManager {
    void onProgressUpdate(UUID sessionId, StudyProgress progress);
}
```

## 5. Factory Method Pattern

```java
// Creating appropriate session types
public final class SessionFactory {
    private SessionFactory() {
    }

    public static Session createSession(StudyMode mode, SessionType type, UUID setId) {
        return switch (mode) {
            case REGULAR -> createRegularSession(type, setId);
            case REVIEW -> createReviewSession(type, setId);
            case TEST -> createTestSession(type, setId);
            case CHALLENGE -> createChallengeSession(type, setId);
        };
    }
}
```

## 6. State Pattern

```java
// Session state management
public sealed interface SessionState
        permits QuestionState, AnswerState, ReviewState {
    SessionState next(UUID sessionId);
    SessionState handleAnswer(UUID sessionId, String answer);
}
```

## 7. Memento Pattern

```java
// For saving/restoring session state
public record SessionMemento(
                UUID sessionId,
                byte[] serializedState
        ) {
    public static SessionMemento saveSession(Session session) {
        // Serialize session state
    }

    public static Session restoreSession(SessionMemento memento) {
        // Restore session state
    }
}
```
