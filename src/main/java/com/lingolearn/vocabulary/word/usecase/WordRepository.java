package com.lingolearn.vocabulary.word.usecase;

import com.lingolearn.vocabulary.word.domain.Difficulty;
import com.lingolearn.vocabulary.word.domain.Word;
import com.lingolearn.vocabulary.word.domain.WordNotFoundException;

import java.util.List;
import java.util.Optional;

public interface WordRepository {
    void save(Word word);
    Optional<Word> findById(Long id);
    List<Word> findAll();
    void deleteById(Long id);
    void deleteByIdOrThrow(Long id) throws WordNotFoundException;
    List<Word> findByDifficulty(Difficulty difficulty);
    Optional<Word> findByOriginalAndTranslation(String original, String translation);
}
