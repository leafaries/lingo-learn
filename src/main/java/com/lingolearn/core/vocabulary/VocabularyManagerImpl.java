package com.lingolearn.core.vocabulary;

import com.lingolearn.core.LingoLearn;

public class VocabularyManagerImpl implements LingoLearn.VocabularyManager {
    private final SetManager setManager;
    private final WordManager wordManager;
    private final CategoryManager categoryManager;

    public VocabularyManagerImpl() {
        this.setManager = new SetManagerImpl();
        this.wordManager = new WordManagerImpl();
        this.categoryManager = new CategoryManagerImpl();
    }

    @Override
    public SetManager sets() {
        return setManager;
    }

    @Override
    public WordManager words() {
        return wordManager;
    }

    @Override
    public CategoryManager categories() {
        return categoryManager;
    }
}
