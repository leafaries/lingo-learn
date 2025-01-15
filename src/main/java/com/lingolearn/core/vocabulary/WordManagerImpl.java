package com.lingolearn.core.vocabulary;

import com.lingolearn.core.LingoLearn;
import com.lingolearn.dtos.WordDTO;

import java.util.List;

public class WordManagerImpl implements LingoLearn.VocabularyManager.WordManager {
    @Override
    public WordDTO create(String original, String translation) {
        return null;
    }

    @Override
    public List<WordDTO> getAll() {
        return List.of();
    }

    @Override
    public void update(WordDTO word, String original, String translation) {

    }

    @Override
    public void delete(WordDTO word) {

    }
}
