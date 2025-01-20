package com.lingolearn;

import com.lingolearn.common.infra.DatabaseManager;
import com.lingolearn.vocabulary.word.adapter.WordController;
import com.lingolearn.vocabulary.word.adapter.WordPresenter;
import com.lingolearn.vocabulary.word.adapter.WordRepositoryImpl;
import com.lingolearn.vocabulary.word.adapter.WordViewModel;
import com.lingolearn.vocabulary.word.adapter.dtos.CreateWordDTO;
import com.lingolearn.vocabulary.word.domain.Difficulty;
import com.lingolearn.vocabulary.word.infra.WordDatabase;
import com.lingolearn.vocabulary.word.infra.WordDatabaseImpl;
import com.lingolearn.vocabulary.word.infra.ui.WordView;
import com.lingolearn.vocabulary.word.usecase.WordInteractor;
import com.lingolearn.vocabulary.word.usecase.WordRepository;

import java.util.List;

public class LingoLearnApplication {
    private final DIContainer container;

    public LingoLearnApplication() {
        this.container = new DIContainer();
        registerDependencies();
    }

    private void registerDependencies() {
        // Infrastructure Layer
        container.register(WordDatabase.class, new WordDatabaseImpl());
//        container.register(VocabularySetDatabase.class, new VocabularySetDatabaseImpl());
//        container.register(CategoryDatabase.class, new CategoryDatabaseImpl());

        // Repositories
        container.register(WordRepository.class,
                new WordRepositoryImpl(container.resolve(WordDatabase.class)));
//        container.register(VocabularySetRepository.class,
//                new VocabularySetRepositoryImpl(container.resolve(VocabularySetDatabase.class)));
//        container.register(CategoryRepository.class,
//                new CategoryRepositoryImpl(container.resolve(CategoryDatabase.class)));

        // ViewModels
        container.register(WordViewModel.class, new WordViewModel());
//        container.register(VocabularySetViewModel.class, new VocabularySetViewModel());
//        container.register(CategoryViewModel.class, new CategoryViewModel());

        // Presenters
        container.register(WordPresenter.class,
                new WordPresenter(container.resolve(WordViewModel.class)));
//        container.register(VocabularySetPresenter.class,
//                new VocabularySetPresenter(container.resolve(VocabularySetViewModel.class)));
//        container.register(CategoryPresenter.class,
//                new CategoryPresenter(container.resolve(CategoryViewModel.class)));

        // Interactors
        container.register(WordInteractor.class, new WordInteractor(
                container.resolve(WordPresenter.class),
                container.resolve(WordRepository.class)
        ));

        // Controllers
        container.register(WordController.class, new WordController(
                container.resolve(WordInteractor.class)
        ));

        // Views
        container.register(WordView.class, new WordView(
                container.resolve(WordController.class),
                container.resolve(WordViewModel.class)
        ));
    }

    public void start() {
        try {
            // Initialize main UI components
            var wordView = container.resolve(WordView.class);
            // Add other main components initialization

            wordView.createNewWord(new CreateWordDTO(
                    "original",
                    "translation",
                    "partOfSpeech",
                    List.of("123", "2222"),
                    Difficulty.HARD
            ));

            runMainLoop();
        } catch (Exception e) {
            System.err.println("Failed to start application: " + e.getMessage());
            shutdown();
        }
    }

    private void runMainLoop() {
        // Main application loop implementation
        // This could be a UI event loop, web server, etc.
        while (true) {
            break;
        }
    }

    public void shutdown() {
        try {
            // Cleanup resources
            DatabaseManager.getInstance().shutdown();
        } catch (Exception e) {
            System.err.println("Error during shutdown: " + e.getMessage());
        }
    }
}
