package com.lingolearn.vocabulary.word.usecase;

import com.lingolearn.vocabulary.set.adapter.viewmodel.WordViewModel;
import com.lingolearn.vocabulary.word.usecase.responsemodel.WordResponseModel;

public interface WordInteractorOutputPort {
    WordViewModel presentWord(WordResponseModel word);
    WordViewModel presentWordNotFound(Long id);
    WordViewModel presentWordDeleted(Long id);
    // TODO: Does following method make sense?
//    WordListViewModel presentWords(List<WordResponseModel> request);
}
