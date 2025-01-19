package com.lingolearn.vocabulary.word.adapter;

import com.lingolearn.vocabulary.word.adapter.dtos.CreateWordDTO;
import com.lingolearn.vocabulary.word.adapter.dtos.UpdateWordDTO;
import com.lingolearn.vocabulary.word.usecase.CreateWordUseCase;
import com.lingolearn.vocabulary.word.usecase.DeleteWordUseCase;
import com.lingolearn.vocabulary.word.usecase.UpdateWordUseCase;
import com.lingolearn.vocabulary.word.usecase.requestmodel.CreateWordRequestModel;
import com.lingolearn.vocabulary.word.usecase.requestmodel.UpdateWordRequestModel;

import java.util.function.*;

public class WordController {
    private final CreateWordUseCase createWordUseCase;
    private final UpdateWordUseCase updateWordUseCase;
    private final DeleteWordUseCase deleteWordUseCase;

    public WordController(CreateWordUseCase createWordUseCase,
                          UpdateWordUseCase updateWordUseCase,
                          DeleteWordUseCase deleteWordUseCase) {
        this.createWordUseCase = createWordUseCase;
        this.updateWordUseCase = updateWordUseCase;
        this.deleteWordUseCase = deleteWordUseCase;
    }

    public void createWord(CreateWordDTO dto) {
        var createWordRequestModel = new CreateWordRequestModel(
                dto.original(),
                dto.translation(),
                dto.partOfSpeech(),
                dto.exampleSentences(),
                dto.difficulty()
        );

        createWordUseCase.execute(createWordRequestModel);
    }

    public void updateWord(UpdateWordDTO dto) {
        var updateWordRequestModel = new UpdateWordRequestModel(
                dto.id(),
                dto.original(),
                dto.translation(),
                dto.partOfSpeech(),
                dto.exampleSentences(),
                dto.difficulty()
        );
        Function<Integer, Integer> a = i -> 77;
        BiFunction<Integer, Integer, Integer> b = (i, j) -> i + j + 13;
        BiConsumer<Integer, Integer> c = (i, j) -> {
        };
        Consumer<Integer> d = (i) -> {
        };
        Predicate predicate;
        BiPredicate biPredicate;

        deleteWordUseCase.execute(updateWordRequestModel);
    }

    public void deleteWord(Long id) {
        deleteWordUseCase.execute(id);
    }
}