package com.lingolearn.vocabulary.word.infra;

import com.lingolearn.vocabulary.word.domain.Difficulty;

import java.util.List;
import java.util.Optional;

public interface WordDatabase {
    WordEntity save(WordEntity entity);
    Optional<WordEntity> findById(Long id);
    List<WordEntity> findAll();
    void deleteById(Long id);
    List<WordEntity> findByDifficulty(Difficulty difficulty);
    Optional<WordEntity> findByOriginalAndTranslation(String original, String translation);
}
