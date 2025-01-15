package com.lingolearn.core.vocabulary;

import com.lingolearn.core.LingoLearn;
import com.lingolearn.dtos.CategoryDTO;
import com.lingolearn.dtos.VocabularySetDTO;
import com.lingolearn.dtos.WordDTO;
import com.lingolearn.entities.CategoryEntity;
import com.lingolearn.entities.VocabularySetEntity;
import com.lingolearn.entities.WordEntity;
import com.lingolearn.repo.VocabularySetRepository;
import com.lingolearn.repo.WordRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SetManagerImpl implements LingoLearn.VocabularyManager.SetManager {
    private final VocabularySetRepository setRepository;
    private final WordRepository wordRepository;

    public SetManagerImpl() {
        this.setRepository = new VocabularySetRepository();
        this.wordRepository = new WordRepository();
    }

    @Override
    public VocabularySetDTO create(String name, String description) {
        VocabularySetEntity entity = new VocabularySetEntity(name, description);
        entity = setRepository.save(entity);
        return mapToDTO(entity);
    }

    @Override
    public List<VocabularySetDTO> getAll() {
        return setRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(VocabularySetDTO set, String name, String description) {
        setRepository.findById(set.id()).ifPresent(entity -> {
            entity.setName(name);
            entity.setDescription(description);
            setRepository.save(entity);
        });
    }

    @Override
    public void addWords(VocabularySetDTO set, List<WordDTO> words) {
        setRepository.findById(set.id()).ifPresent(setEntity -> {
            words.forEach(wordDTO ->
                    wordRepository.findById(wordDTO.id()).ifPresent(setEntity::addWord)
            );
            setRepository.save(setEntity);
        });
    }

    @Override
    public void removeWords(VocabularySetDTO set, List<WordDTO> words) {
        setRepository.findById(set.id()).ifPresent(setEntity -> {
            words.forEach(wordDTO ->
                    wordRepository.findById(wordDTO.id()).ifPresent(setEntity::removeWord)
            );
            setRepository.save(setEntity);
        });
    }

    @Override
    public void delete(VocabularySetDTO set) {
        setRepository.findById(set.id()).ifPresent(setRepository::delete);
    }

    private VocabularySetDTO mapToDTO(VocabularySetEntity entity) {
        return new VocabularySetDTO(
                entity.getName(),
                entity.getDescription(),
                entity.getCategory() != null ? mapCategoryToDTO(entity.getCategory()) : null,
                entity.getWords().stream()
                        .map(this::mapWordToDTO)
                        .collect(Collectors.toList()),
                entity.getCreatedAt().toInstant(),
                entity.getLastModifiedAt().toInstant()
        );
    }

    private WordDTO mapWordToDTO(WordEntity entity) {
        return new WordDTO(
                entity.getOriginal(),
                entity.getTranslation(),
                entity.getDifficulty(),
                entity.getCreatedAt().toInstant(),
                entity.getLastModifiedAt().toInstant()
        );
    }

    private CategoryDTO mapCategoryToDTO(CategoryEntity entity) {
        return new CategoryDTO(
                entity.getName(),
                entity.getCreatedAt().toInstant(),
                entity.getLastModifiedAt().toInstant()
        );
    }
}
