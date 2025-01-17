package com.lingolearn.vocabulary.word.usecase;

import com.lingolearn.vocabulary.word.domain.Difficulty;
import com.lingolearn.vocabulary.word.domain.Word;

import java.util.List;
import java.util.Optional;

public interface WordRepository {
    Word save(Word word);
    Optional<Word> findById(Long id);
    List<Word> findAll();
    void deleteById(Long id);
    List<Word> findByDifficulty(Difficulty difficulty);
    Optional<Word> findByOriginalAndTranslation(String original, String translation);
}
