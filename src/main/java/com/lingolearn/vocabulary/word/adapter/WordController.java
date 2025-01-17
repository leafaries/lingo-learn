package com.lingolearn.vocabulary.word.adapter;

import com.lingolearn.vocabulary.word.adapter.dtos.CreateWordDTO;
import com.lingolearn.vocabulary.word.adapter.dtos.RecordAttemptDTO;
import com.lingolearn.vocabulary.word.adapter.dtos.UpdateWordDTO;
import com.lingolearn.vocabulary.word.adapter.dtos.WordDTO;
import com.lingolearn.vocabulary.word.domain.Word;
import com.lingolearn.vocabulary.word.usecase.WordInteractorInputPort;
import com.lingolearn.vocabulary.word.usecase.requestmodel.CreateWordRequestModel;
import com.lingolearn.vocabulary.word.usecase.requestmodel.RecordAttemptRequestModel;
import com.lingolearn.vocabulary.word.usecase.requestmodel.UpdateWordRequestModel;
import com.lingolearn.vocabulary.word.usecase.responsemodel.WordResponseModel;

import java.util.List;
import java.util.Optional;

public class WordController {
    private final WordInteractorInputPort wordInteractorInputPort;
    private final WordInteractorPresenter wordPresenter;

    public WordController(WordInteractorInputPort wordInteractorInputPort, WordInteractorPresenter wordPresenter) {
        this.wordInteractorInputPort = wordInteractorInputPort;
        this.wordPresenter = wordPresenter;
    }

    public WordDTO createWord(CreateWordDTO dto) {
        CreateWordRequestModel requestModel = new CreateWordRequestModel(
                dto.original(),
                dto.translation(),
                dto.partOfSpeech(),
                dto.exampleSentences(),
                dto.difficulty()
        );

        WordResponseModel response = wordInteractorInputPort.createWord(requestModel);
        return mapToDTO(response);
    }

    public WordDTO updateWord(UpdateWordDTO dto) {
        UpdateWordRequestModel requestModel = new UpdateWordRequestModel(
                dto.id(),
                dto.original(),
                dto.translation(),
                dto.partOfSpeech(),
                dto.exampleSentences(),
                dto.difficulty()
        );

        WordResponseModel response = wordInteractorInputPort.updateWord(requestModel);
        return mapToDTO(response);
    }

    public void recordAttempt(RecordAttemptDTO dto) {
        RecordAttemptRequestModel requestModel = new RecordAttemptRequestModel(
                dto.wordId(),
                dto.wasCorrect()
        );

        wordInteractorInputPort.recordAttempt(requestModel);
    }

    public Optional<WordDTO> findById(Long id) {
        return wordInteractorInputPort.findById(id)
                .map(this::mapWordToDTO);
    }

    public List<WordDTO> findAll() {
        return wordInteractorInputPort.findAll()
                .stream()
                .map(this::mapWordToDTO)
                .toList();
    }

    public List<WordDTO> findDifficultWords() {
        return wordInteractorInputPort.findDifficultWords()
                .stream()
                .map(this::mapWordToDTO)
                .toList();
    }

    public List<WordDTO> findDueForReview() {
        return wordInteractorInputPort.findDueForReview()
                .stream()
                .map(this::mapWordToDTO)
                .toList();
    }

    public void deleteWord(Long id) {
        wordInteractorInputPort.deleteWord(id);
    }

    private WordDTO mapToDTO(WordResponseModel response) {
        return new WordDTO(
                response.id(),
                response.original(),
                response.translation(),
                response.partOfSpeech(),
                response.exampleSentences(),
                response.difficulty(),
                response.lastReviewed(),
                response.timesReviewed(),
                response.correctAnswers(),
                response.successRate()
        );
    }

    private WordDTO mapWordToDTO(Word word) {
        return new WordDTO(
                word.id(),
                word.original(),
                word.translation(),
                word.partOfSpeech(),
                word.exampleSentences(),
                word.difficulty(),
                word.lastReviewed(),
                word.timesReviewed(),
                word.correctAnswers(),
                word.getSuccessRate()
        );
    }
}
