package com.lingolearn.vocabulary.word.adapter;

import com.lingolearn.vocabulary.set.adapter.viewmodel.WordViewModel;
import com.lingolearn.vocabulary.word.domain.Difficulty;
import com.lingolearn.vocabulary.word.usecase.WordInteractorOutputPort;
import com.lingolearn.vocabulary.word.usecase.responsemodel.WordResponseModel;

import java.time.format.DateTimeFormatter;
import java.util.Map;

public class WordInteractorPresenter implements WordInteractorOutputPort {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
    private static final Map<Difficulty, String> DIFFICULTY_LABELS = Map.of(
            Difficulty.EASY, "Beginner Friendly",
            Difficulty.MEDIUM, "Intermediate",
            Difficulty.HARD, "Advanced"
    );

    private WordViewModel presentedWord;
    private Long deletedWordId;

    @Override
    public WordResponseModel presentWord(WordResponseModel word) {
        this.presentedWord = new WordViewModel(
                word.id(),
                word.original(),
                word.translation(),
                word.partOfSpeech(),
                word.exampleSentences(),
                word.difficulty(),
                word.lastReviewed(),
                word.timesReviewed(),
                word.correctAnswers(),
                word.successRate(),
                // View-specific transformations
                DIFFICULTY_LABELS.getOrDefault(word.difficulty(), "Unknown"),
                word.lastReviewed() != null ? word.lastReviewed().format(DATE_FORMATTER) : "Never reviewed",
                String.format("%.1f%%", word.successRate())
        );
        return word;
    }

    @Override
    public void presentWordDeleted(Long id) {
        this.deletedWordId = id;
    }

    public WordViewModel getPresentedWord() {
        return presentedWord;
    }

    public Long getDeletedWordId() {
        return deletedWordId;
    }
}
