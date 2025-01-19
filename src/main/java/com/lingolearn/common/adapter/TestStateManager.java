package com.lingolearn.common.adapter;

import com.lingolearn.vocabulary.category.adapter.FakeCategoryRepository;
import com.lingolearn.vocabulary.set.adapter.testapi.FakeVocabularySetRepository;
import com.lingolearn.vocabulary.word.adapter.FakeWordRepository;

/**
 * Manages test state and provides fake implementations of repositories
 */
public class TestStateManager {
    private final FakeWordRepository wordRepository;
    private final FakeVocabularySetRepository setRepository;
    private final FakeCategoryRepository categoryRepository;

    public TestStateManager() {
        this.wordRepository = new FakeWordRepository();
        this.setRepository = new FakeVocabularySetRepository();
        this.categoryRepository = new FakeCategoryRepository();
    }

    public FakeWordRepository getWordRepository() {
        return wordRepository;
    }

    public FakeVocabularySetRepository getSetRepository() {
        return setRepository;
    }

    public FakeCategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public void reset() {
        wordRepository.clear();
        setRepository.clear();
        categoryRepository.clear();
    }
}
