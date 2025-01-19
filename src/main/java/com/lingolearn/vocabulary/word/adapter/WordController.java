package com.lingolearn.vocabulary.word.adapter;

import com.lingolearn.vocabulary.word.adapter.dtos.CreateWordDTO;
import com.lingolearn.vocabulary.word.adapter.dtos.RecordAttemptDTO;
import com.lingolearn.vocabulary.word.adapter.dtos.UpdateWordDTO;
import com.lingolearn.vocabulary.word.domain.Difficulty;
import com.lingolearn.vocabulary.word.usecase.WordInteractorInputPort;
import com.lingolearn.vocabulary.word.usecase.requestmodel.CreateWordRequestModel;
import com.lingolearn.vocabulary.word.usecase.requestmodel.UpdateWordRequestModel;

public class WordController {
    private final WordInteractorInputPort wordInteractor;

    public WordController(WordInteractorInputPort wordInteractor) {
        this.wordInteractor = wordInteractor;
    }

    public void createWord(CreateWordDTO dto) {
        var request = new CreateWordRequestModel(
                dto.original(),
                dto.translation(),
                dto.partOfSpeech(),
                dto.exampleSentences(),
                dto.difficulty()
        );
        wordInteractor.createWord(request);
    }

    public void updateWord(UpdateWordDTO dto) {
        var request = new UpdateWordRequestModel(
                dto.id(),
                dto.original(),
                dto.translation(),
                dto.partOfSpeech(),
                dto.exampleSentences(),
                dto.difficulty()
        );
        wordInteractor.updateWord(request);
    }

    public void recordAttempt(RecordAttemptDTO dto) {
        wordInteractor.recordAttempt(dto.wordId(), dto.wasCorrect());
    }

    public void deleteWord(Long id) {
        wordInteractor.deleteWord(id);
    }

    public void getAllWords() {
        wordInteractor.getAllWords();
    }

    public void getWord(Long id) {
        wordInteractor.getWord(id);
    }

    public void getWordsByDifficulty(Difficulty difficulty) {
        wordInteractor.getWordsByDifficulty(difficulty);
    }
}
