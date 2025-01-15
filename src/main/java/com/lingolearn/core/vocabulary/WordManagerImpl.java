package com.lingolearn.core.vocabulary;

import com.lingolearn.core.LingoLearn;
import com.lingolearn.dtos.WordDTO;
import com.lingolearn.entities.WordEntity;
import com.lingolearn.repos.WordRepository;

import java.util.List;
import java.util.stream.Collectors;

public class WordManagerImpl implements LingoLearn.VocabularyManager.WordManager {
    private final WordRepository wordRepository;

    public WordManagerImpl() {
        this.wordRepository = new WordRepository();
    }

    @Override
    public WordDTO create(String original, String translation) {
        WordEntity entity = new WordEntity(original, translation);
        entity = wordRepository.save(entity);
        return mapToDTO(entity);
    }

    @Override
    public List<WordDTO> getAll() {
        return wordRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(WordDTO word, String original, String translation) {
        wordRepository.findById(word.id()).ifPresent(entity -> {
            entity.setOriginal(original);
            entity.setTranslation(translation);
            wordRepository.save(entity);
        });
    }

    @Override
    public void delete(WordDTO word) {
        wordRepository.findById(word.id()).ifPresent(wordRepository::delete);
    }

    private WordDTO mapToDTO(WordEntity entity) {
        return new WordDTO(
                entity.getOriginal(),
                entity.getTranslation(),
                entity.getDifficulty(),
                entity.getCreatedAt().toInstant(),
                entity.getLastModifiedAt().toInstant()
        );
    }
}
