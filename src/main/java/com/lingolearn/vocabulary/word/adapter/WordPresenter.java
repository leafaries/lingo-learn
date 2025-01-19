package com.lingolearn.vocabulary.word.adapter;

import com.lingolearn.vocabulary.word.usecase.WordInteractorOutputPort;
import com.lingolearn.vocabulary.word.usecase.responsemodel.CreateWordResponseModel;
import com.lingolearn.vocabulary.word.usecase.responsemodel.GetWordResponseModel;
import com.lingolearn.vocabulary.word.usecase.responsemodel.UpdateWordResponseModel;

import java.util.List;

public class WordPresenter implements WordInteractorOutputPort {
    private final WordViewModel viewModel;

    public WordPresenter(WordViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentCreatedWord(CreateWordResponseModel response) {
        try {
            viewModel.setLoading(true);
            var wordViewModel = mapToViewModel(response);
            viewModel.addWord(wordViewModel);
            viewModel.setCurrentWord(wordViewModel);
        } catch (Exception e) {
            viewModel.setError("Failed to create word: " + e.getMessage());
        } finally {
            viewModel.setLoading(false);
        }
    }

    @Override
    public void presentUpdatedWord(UpdateWordResponseModel response) {
        try {
            viewModel.setLoading(true);
            var wordViewModel = mapToViewModel(response);
            viewModel.updateWord(wordViewModel);
        } catch (Exception e) {
            viewModel.setError("Failed to update word: " + e.getMessage());
        } finally {
            viewModel.setLoading(false);
        }
    }

    @Override
    public void presentWord(GetWordResponseModel response) {
        try {
            viewModel.setLoading(true);
            var wordViewModel = mapToViewModel(response);
            viewModel.setCurrentWord(wordViewModel);
        } catch (Exception e) {
            viewModel.setError("Failed to load word: " + e.getMessage());
        } finally {
            viewModel.setLoading(false);
        }
    }

    @Override
    public void presentWords(List<GetWordResponseModel> response) {
        try {
            viewModel.setLoading(true);
            var wordViewModels = response.stream()
                    .map(this::mapToViewModel)
                    .toList();
            viewModel.setWordList(wordViewModels);
        } catch (Exception e) {
            viewModel.setError("Failed to load words: " + e.getMessage());
        } finally {
            viewModel.setLoading(false);
        }
    }

    @Override
    public void presentWordDeleted(Long id) {
        try {
            viewModel.setLoading(true);
            viewModel.removeWord(id);
        } catch (Exception e) {
            viewModel.setError("Failed to delete word: " + e.getMessage());
        } finally {
            viewModel.setLoading(false);
        }
    }

    @Override
    public void presentAttemptRecorded(Long wordId) {
        try {
            viewModel.setLoading(true);
            // Optionally update the word's statistics here
        } catch (Exception e) {
            viewModel.setError("Failed to record attempt: " + e.getMessage());
        } finally {
            viewModel.setLoading(false);
        }
    }

    private WordDisplayModel mapToViewModel(CreateWordResponseModel response) {
        return new WordDisplayModel(
                response.id(),
                response.original(),
                response.translation(),
                response.partOfSpeech(),
                response.exampleSentences(),
                response.difficulty()
        );
    }

    private WordDisplayModel mapToViewModel(UpdateWordResponseModel response) {
        return new WordDisplayModel(
                response.id(),
                response.original(),
                response.translation(),
                response.partOfSpeech(),
                response.exampleSentences(),
                response.difficulty()
        );
    }

    private WordDisplayModel mapToViewModel(GetWordResponseModel response) {
        return new WordDisplayModel(
                response.id(),
                response.original(),
                response.translation(),
                response.partOfSpeech(),
                response.exampleSentences(),
                response.difficulty()
        );
    }
}
