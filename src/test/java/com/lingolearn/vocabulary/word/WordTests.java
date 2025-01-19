package com.lingolearn.vocabulary.word;

import com.lingolearn.common.adapter.TestStateManager;
import com.lingolearn.vocabulary.word.adapter.WordTestAPI;
import com.lingolearn.vocabulary.word.adapter.WordViewModel;
import com.lingolearn.vocabulary.word.adapter.dtos.CreateWordDTO;
import com.lingolearn.vocabulary.word.adapter.dtos.UpdateWordDTO;
import com.lingolearn.vocabulary.word.domain.Difficulty;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Word Module Tests")
class WordModuleTest {
    private WordTestAPI testAPI;
    private WordViewModel viewModel;

    @BeforeEach
    void setUp() {
        testAPI = new WordTestAPI(new TestStateManager());
        viewModel = testAPI.getViewModel();
    }

    @AfterEach
    void tearDown() {
        testAPI.reset();
    }

    @Nested
    @DisplayName("Word Creation Tests")
    class WordCreationTests {
        @Test
        @DisplayName("Should successfully create a new word")
        void createWord() {
            // Arrange
            var dto = new CreateWordDTO(
                    "hello",
                    "cześć",
                    "noun",
                    List.of("Hello, world!"),
                    Difficulty.EASY
            );

            // Act
            testAPI.createWord(dto);

            // Assert
            assertFalse(viewModel.getState().isLoading());
            assertNull(viewModel.getState().errorMessage());
            assertEquals(1, viewModel.getState().wordList().size());

            var createdWord = viewModel.getState().wordList().getFirst();
            assertEquals("hello", createdWord.original());
            assertEquals("cześć", createdWord.translation());
        }

        @Test
        @DisplayName("Should prevent duplicate words")
        void preventDuplicateWords() {
            // Arrange
            var dto = new CreateWordDTO(
                    "hello",
                    "cześć",
                    "noun",
                    List.of("Hello, world!"),
                    Difficulty.EASY
            );

            // Act
            testAPI.createWord(dto);
            testAPI.createWord(dto);

            // Assert
            assertNotNull(viewModel.getState().errorMessage());
            assertTrue(viewModel.getState().errorMessage().contains("already exists"));
            assertEquals(1, viewModel.getState().wordList().size());
        }

        @ParameterizedTest
        @EnumSource(Difficulty.class)
        @DisplayName("Should create words with different difficulties")
        void createWordWithDifferentDifficulties(Difficulty difficulty) {
            // Arrange
            var dto = new CreateWordDTO(
                    "test" + difficulty,
                    "test" + difficulty,
                    "noun",
                    List.of("Test sentence"),
                    difficulty
            );

            // Act
            testAPI.createWord(dto);

            // Assert
            var createdWord = viewModel.getState().wordList().getFirst();
            assertEquals(difficulty, createdWord.difficulty());
        }
    }

    @Nested
    @DisplayName("Word Update Tests")
    class WordUpdateTests {
        private Long wordId;

        @BeforeEach
        void createInitialWord() {
            var dto = new CreateWordDTO(
                    "initial",
                    "początkowy",
                    "adjective",
                    List.of("Initial test"),
                    Difficulty.MEDIUM
            );
            testAPI.createWord(dto);
            wordId = viewModel.getState().wordList().getFirst().id();
        }

        @Test
        @DisplayName("Should update existing word")
        void updateWord() {
            // Arrange
            var updateDto = new UpdateWordDTO(
                    wordId,
                    "updated",
                    "zaktualizowany",
                    "adjective",
                    List.of("Updated test"),
                    Difficulty.HARD
            );

            // Act
            testAPI.updateWord(updateDto);

            // Assert
            var updatedWord = viewModel.getState().wordList().get(0);
            assertEquals("updated", updatedWord.original());
            assertEquals("zaktualizowany", updatedWord.translation());
            assertEquals(Difficulty.HARD, updatedWord.difficulty());
        }

        @Test
        @DisplayName("Should handle update of non-existent word")
        void updateNonExistentWord() {
            // Arrange
            var updateDto = new UpdateWordDTO(
                    999L,
                    "nonexistent",
                    "nieistniejący",
                    "noun",
                    List.of(),
                    Difficulty.EASY
            );

            // Act
            testAPI.updateWord(updateDto);

            // Assert
            assertNotNull(viewModel.getState().errorMessage());
            assertTrue(viewModel.getState().errorMessage().contains("not found"));
        }
    }

    @Nested
    @DisplayName("Word Learning Tests")
    class WordLearningTests {
        private Long wordId;

        @BeforeEach
        void createTestWord() {
            var dto = new CreateWordDTO(
                    "learn",
                    "uczyć się",
                    "verb",
                    List.of("I learn every day"),
                    Difficulty.MEDIUM
            );
            testAPI.createWord(dto);
            wordId = viewModel.getState().wordList().getFirst().id();
        }

        @Test
        @DisplayName("Should record successful attempt")
        void recordSuccessfulAttempt() {
            // Act
            testAPI.recordAttempt(wordId, true);

            // Assert
            assertFalse(viewModel.getState().isLoading());
            assertNull(viewModel.getState().errorMessage());

            var word = testAPI.getWordDetails(wordId);
            assertEquals(1, word.timesReviewed());
            assertEquals(1, word.correctAnswers());
        }

        @Test
        @DisplayName("Should record failed attempt")
        void recordFailedAttempt() {
            // Act
            testAPI.recordAttempt(wordId, false);

            // Assert
            var word = testAPI.getWordDetails(wordId);
            assertEquals(1, word.timesReviewed());
            assertEquals(0, word.correctAnswers());
        }
    }

    @Nested
    @DisplayName("Word Deletion Tests")
    class WordDeletionTests {
        @Test
        @DisplayName("Should delete existing word")
        void deleteWord() {
            // Arrange
            var dto = new CreateWordDTO(
                    "delete",
                    "usunąć",
                    "verb",
                    List.of("Delete this"),
                    Difficulty.EASY
            );
            testAPI.createWord(dto);
            var wordId = viewModel.getState().wordList().getFirst().id();

            // Act
            testAPI.deleteWord(wordId);

            // Assert
            assertTrue(viewModel.getState().wordList().isEmpty());
            assertFalse(testAPI.wordExists(wordId));
        }

        @Test
        @DisplayName("Should handle deletion of non-existent word")
        void deleteNonExistentWord() {
            // Act
            testAPI.deleteWord(999L);

            // Assert
            assertNotNull(viewModel.getState().errorMessage());
            assertTrue(viewModel.getState().errorMessage().contains("not found"));
        }
    }

    @Nested
    @DisplayName("Word Query Tests")
    class WordQueryTests {
        @BeforeEach
        void setupWords() {
            List.of(
                    new CreateWordDTO("word1", "słowo1", "noun", List.of(), Difficulty.EASY),
                    new CreateWordDTO("word2", "słowo2", "noun", List.of(), Difficulty.MEDIUM),
                    new CreateWordDTO("word3", "słowo3", "noun", List.of(), Difficulty.HARD)
            ).forEach(testAPI::createWord);
        }

        @Test
        @DisplayName("Should get all words")
        void getAllWords() {
            // Act
            testAPI.getAllWords();

            // Assert
            assertEquals(3, viewModel.getState().wordList().size());
        }

        @Test
        @DisplayName("Should filter words by difficulty")
        void getWordsByDifficulty() {
            // Act
            testAPI.getWordsByDifficulty(Difficulty.EASY);

            // Assert
            assertEquals(1, viewModel.getState().wordList().size());
            assertEquals(Difficulty.EASY, viewModel.getState().wordList().getFirst().difficulty());
        }
    }
}
