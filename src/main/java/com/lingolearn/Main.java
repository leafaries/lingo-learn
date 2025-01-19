package com.lingolearn;

import com.lingolearn.common.infra.DatabaseManager;
import com.lingolearn.vocabulary.word.adapter.WordController;
import com.lingolearn.vocabulary.word.adapter.WordPresenter;
import com.lingolearn.vocabulary.word.adapter.WordRepositoryImpl;
import com.lingolearn.vocabulary.word.adapter.WordViewModel;
import com.lingolearn.vocabulary.word.adapter.dtos.CreateWordDTO;
import com.lingolearn.vocabulary.word.domain.Difficulty;
import com.lingolearn.vocabulary.word.infra.WordDatabaseImpl;
import com.lingolearn.vocabulary.word.infra.ui.WordView;
import com.lingolearn.vocabulary.word.usecase.WordInteractor;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        var vm = new SomeRandomViewModel();
//        var v = new View(vm);
//        vm.mainBusinessLogic();

        // Testing of word functionality
        var wordDatabase = new WordDatabaseImpl();
        var wordRepository = new WordRepositoryImpl(wordDatabase);

        var wordViewModel = new WordViewModel();
        var wordPresenter = new WordPresenter(wordViewModel);

        var wordInteractor = new WordInteractor(wordPresenter, wordRepository);
        var wordController = new WordController(wordInteractor);
        var wordView = new WordView(wordController, wordViewModel);
        wordView.createNewWord(new CreateWordDTO(
                "original",
                "translation",
                "partOfSpeech",
                List.of("123", "2222"),
                Difficulty.HARD
        ));

        DatabaseManager.getInstance().shutdown();
    }
}
