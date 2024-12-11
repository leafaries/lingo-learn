# lingo-learn-hub

**Lingo-learn-hub** is a Java library developed as a sample university project to test skills in design patterns.
The project emphasizes creating reusable core functionality for language learning, which can easily be extended
by adding custom user interface (UI) modules.

## Problem Description

The following describes the problem or requirements that this repository aims to address:

> "Aplikacja do nauki języka. Aplikacja umożliwia użytkownikowi przyswajanie słownictwa i sprawdzanie swojej wiedzy
> w trybach takich jak fiszki (wyświetlanie słowa lub tłumaczenia z oczekiwaniem na odpowiedź użytkownika i prezentacja
> poprawnej odpowiedzi), wybór poprawnego tłumaczenia spośród kilku opcji, samodzielne wpisywanie tłumaczenia.
> Aplikacja zachęca do regularnej nauki przy pomocy codziennych wyzwań i umożliwia powtórki słów, z którymi użytkownik
> miał problemy. Testy wiedzy pozwalają sprawdzić znajomość słów na podstawie wybranych zestawów. Użytkownik może
> tworzyć zestawy słówek, grupować je w kategorie (np. „Podróże”, „Zakupy”), śledzić postępy w nauce i generować
> raporty. Aplikacja oferuje eksport i import danych."

## Overview

The project is designed to facilitate the following:

- Core functionality for vocabulary acquisition and knowledge testing.
- Multiple learning modes, including:
    - Flashcards (showing a vocabularyWord or its translation with user interaction).
    - Multiple-choice translation selection.
    - Manual translation input.
- Encouragement for regular learning through daily challenges and progress tracking.
- Customization with user-created vocabularyWord sets, categories (e.g., “Travel,” “Shopping”), and detailed progress
  reports.
- Support for data import/export for ease of use and scalability.

The modular architecture makes it easy to integrate new UI modules, such as desktop apps (Swing, JavaFX), mobile apps,
or web apps, without modifying the core logic.

## Installation

Clone the repository to get started:

```bash
git clone https://github.com/leafaries/lingo-learn-hub.git
```

Then, build the project using your preferred Java build tool (e.g., Maven or Gradle).

## Usage

The core module is designed to handle the application's main functionality. Developers can fork the repository
and create custom UI modules that connect to the core for language-learning features. Here's an example of how you might
interact with the core module in Java:

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+

### Basic Usage

```java
import com.lingolearn.LingoLearn;
import com.lingolearn.oki.domain.vocabulary.VocabularySet;
import com.lingolearn.tobeorganizedclasses.infrastructure.config.Configuration;

public class Example {

    public static void main(String[] args) {
        // Initialize the library
        LingoLearn lingoLearn = new LingoLearn();

        // Configure learning preferences
        Configuration config = new Configuration();
        config.setLearningStrategy("flashcard");
        config.setDailyChallengesLimit(5);
        lingoLearn.configure(config);

        // Create a vocabulary set
        VocabularySet vocabularySet = new VocabularySet("Travel Vocabulary");
        vocabularySet.addWord(new VocabularyWord("hello", "hola", "greetings", "easy"));

        // Start learning session
        lingoLearn.startLearning("user123", vocabularySet);

        // Get learning statistics
        ChallengeStatistics stats = lingoLearn.getUserStatistics("user123");
        System.out.println(stats.toString());
    }
}
```

## Features

### Learning Modes

- **Flashcards**: Display word/translation pairs with user response validation
- **Multiple Choice**: Present options for translation selection
- **Translation Input**: Allow manual translation entry

### Progress Tracking

- Track accuracy per word
- Identify difficult words
- Monitor daily learning streaks
- Generate detailed progress reports

### Data Management

- Create and manage vocabulary sets
- Organize words into categories
- Import/export functionality
- Automatic data backups

## Architecture

The library uses several design patterns to ensure extensibility and maintainability:

- **Strategy Pattern**: For different learning modes
- **Observer Pattern**: For progress tracking
- **Composite Pattern**: For vocabulary organization
- **Factory Pattern**: For creating learning sessions
- **Command Pattern**: For challenge management

## For Developers

### Creating a Custom UI

The library is UI-agnostic. Implement your preferred UI by:

1. Creating view components for each learning mode
2. Implementing the progress display interface
3. Managing user interactions with the library's API

Example structure:

```java
public class CustomLearningView {

    private final LingoLearn lingoLearn;
    private final String userId;

    public void displayFlashcard(VocabularyWord word) {
        // Your UI implementation
    }

    public void handleUserResponse(String response) {
        // Process user input
    }
}
```

### Data Persistence

The library handles data persistence automatically using a file-based system. Data is stored in:

```
/data
  ├── vocabulary.json
  ├── progress.json
  └── settings.json
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
