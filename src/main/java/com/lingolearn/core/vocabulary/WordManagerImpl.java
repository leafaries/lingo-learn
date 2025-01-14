package com.lingolearn.core.vocabulary;

import com.lingolearn.core.LingoLearn;
import com.lingolearn.models.Word;

import java.util.List;

public class WordManagerImpl implements LingoLearn.VocabularyManager.WordManager {
    @Override
    public Word create(String original, String translation) {
        return null;
    }

    @Override
    public List<Word> getAll() {
        return List.of();
    }

    @Override
    public void update(Word word, String original, String translation) {

    }

    @Override
    public void delete(Word word) {

    }
}
