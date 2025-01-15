# LingoLearn Project Design Documentation

## Architecture Overview

The project follows a layered architecture with clear separation of concerns:

### 1. Core Layer

- Base entities (WordEntity, CategoryEntity, etc.)
- JPA repositories for data access
- Core business logic

### 2. Service Layer

- Main facade interface (LingoLearn)
- Implementations of core services (StudyManager, ChallengeManager, etc.)

### 3. DTO Layer

We've organized DTOs into logical packages with clear responsibilities:

```
com.lingolearn.dtos/
├── core/
│   ├── TimeTrackedDTO
│   ├── StatisticsDTO
│   └── LearningItemDTO
├── study/
│   ├── AnswerDTO
│   ├── StudyProgressDTO
│   └── StudyResultDTO
├── challenge/
│   ├── ChallengeDTO
│   └── TestResultDTO
├── statistics/
│   ├── StudyStatisticsDTO
│   ├── ReportDTO
│   └── ReportConfigDTO
└── config/
    └── PreferenceConfigDTO
```

### 4. User Facade Layer

We've added a user-friendly facade (LingoLearnApp) that simplifies common operations:

- VocabularyManager for word/set management
- LearningSession for study sessions
- DailyChallenges for challenge management
- Progress for statistics and reporting
- DataManagement for import/export

## Key Design Decisions

### 1. DTO Design

- DTOs are immutable (using Java records)
- No IDs in DTOs (managed internally by repositories)
- Clear interfaces for common patterns (TimeTrackedDTO, StatisticsDTO)
- Consistent naming and structure

### 2. Facade Pattern

- Two-layer facade approach:
    1. Core facade (LingoLearn) for complete API access
    2. User facade (LingoLearnApp) for simplified common operations
- Allows both simple and advanced usage

### 3. Data Management

- Immutable DTOs for data transfer
- Mutable entities managed by JPA
- Clear separation between persistence and business logic

### 4. Study Session Management

- Different modes (flashcards, multiple choice, manual input)
- Progress tracking through StudyProgressDTO
- Session results through StudyResultDTO

### 5. Statistics & Reporting

- Comprehensive statistics tracking
- Flexible report configuration
- Multiple metrics support

## Usage Patterns

### Basic Usage (User Facade)

```java
// Create vocabulary set
VocabularySetDTO set = LingoLearnApp.VocabularyManager
    .new SetCreator("Travel", "Travel vocabulary")
    .addWord("hello", "hola")
    .inCategory("Travel")
    .create();

// Start study session
LearningSession.Session session = LingoLearnApp.LearningSession
    .flashcards(set);
```

### Advanced Usage (Core Facade)

```java
LingoLearn lingoLearn = new LingoLearnImpl();
WordDTO word = lingoLearn.vocabulary().words()
    .create("hello", "hola");
```

## Planned Improvements

1. Testing Support

- Add test utilities
- Create mock repositories
- Add integration test support

2. Error Handling

- Create specific exceptions
- Add validation in builders
- Implement consistent error handling

3. Performance Optimization

- Add caching for frequently accessed data
- Implement connection pooling
- Optimize database queries

4. Additional Features

- Enhanced reporting capabilities
- More study modes
- Advanced statistics tracking

## Current Status

The project currently has:

- Complete DTO structure
- Basic repository implementation
- Core facade interface
- User-friendly facade
- Basic entity structure

Next steps include:

1. Implementing missing manager classes
2. Adding comprehensive testing
3. Implementing error handling
4. Adding documentation and examples
