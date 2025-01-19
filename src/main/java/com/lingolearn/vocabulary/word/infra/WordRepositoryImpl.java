package com.lingolearn.vocabulary.word.infra;

import com.lingolearn.vocabulary.word.domain.Difficulty;
import com.lingolearn.vocabulary.word.domain.Word;
import com.lingolearn.vocabulary.word.domain.WordNotFoundException;
import com.lingolearn.vocabulary.word.usecase.WordRepository;

import java.util.List;
import java.util.Optional;

public class WordRepositoryImpl implements WordRepository {
    private final WordDatabase database;

    public WordRepositoryImpl(WordDatabase database) {
        this.database = database;
    }

    @Override
    public Word save(Word word) {
        var entity = WordMapper.toEntity(word);
        var savedEntity = database.save(entity);
        return WordMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Word> findById(Long id) {
        return database.findById(id)
                .map(WordMapper::toDomain);
    }

    @Override
    public List<Word> findAll() {
        return WordMapper.toDomainList(database.findAll());
    }

    @Override
    public void deleteById(Long id) {
        database.deleteById(id);
    }

    @Override
    public void deleteByIdOrThrow(Long id) throws WordNotFoundException {
        findById(id).orElseThrow(() -> new WordNotFoundException(id));
        database.deleteById(id);
    }

    @Override
    public List<Word> findByDifficulty(Difficulty difficulty) {
        return WordMapper.toDomainList(
                database.findByDifficulty(difficulty)
        );
    }

    @Override
    public Optional<Word> findByOriginalAndTranslation(String original, String translation) {
        return database.findByOriginalAndTranslation(original, translation)
                .map(WordMapper::toDomain);
    }
}
