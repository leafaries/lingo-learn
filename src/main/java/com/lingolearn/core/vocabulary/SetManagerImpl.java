package com.lingolearn.core.vocabulary;

import com.lingolearn.core.LingoLearn;
import com.lingolearn.models.VocabularySet;
import com.lingolearn.models.Word;

import java.util.List;

public class SetManagerImpl implements LingoLearn.VocabularyManager.SetManager {
    @Override
    public VocabularySet create(String name, String description) {
        return null;
    }

    @Override
    public List<VocabularySet> getAll() {
        return List.of();
    }

    @Override
    public void update(VocabularySet set, String name, String description) {

    }

    @Override
    public void addWords(VocabularySet set, List<Word> words) {

    }

    @Override
    public void removeWords(VocabularySet set, List<Word> words) {

    }

    @Override
    public void delete(VocabularySet set) {

    }
}
