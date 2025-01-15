package com.lingolearn.core.vocabulary;

import com.lingolearn.core.LingoLearn;
import com.lingolearn.dtos.VocabularySetDTO;
import com.lingolearn.dtos.WordDTO;
import com.lingolearn.entities.VocabularySetEntity;
import com.lingolearn.entities.WordEntity;
import com.lingolearn.repo.VocabularySetRepository;
import com.lingolearn.repo.WordRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SetManagerImpl implements LingoLearn.VocabularyManager.SetManager {
    private final VocabularySetRepository repository;
    private final WordRepository wordRepository;

    public SetManagerImpl() {
        this.repository = new VocabularySetRepository();
        this.wordRepository = new WordRepository();
    }

    @Override
    public VocabularySetDTO create(String name, String description) {
        VocabularySetEntity entity = new VocabularySetEntity(name, description);
        entity = repository.save(entity);
        return mapToModel(entity);
    }

    @Override
    public List<VocabularySetDTO> getAll() {
        return List.of();
    }

    @Override
    public void update(VocabularySetDTO set, String name, String description) {

    }

    @Override
    public void addWords(VocabularySetDTO set, List<WordDTO> words) {

    }

    @Override
    public void removeWords(VocabularySetDTO set, List<WordDTO> words) {

    }

    @Override
    public void delete(VocabularySetDTO set) {

    }

    private VocabularySetDTO mapToDTO(VocabularySetEntity entity) {
        return new VocabularySetDTO(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                null, // category will be mapped when implemented
                entity.getWords().stream()
                        .map(this::mapWordToDTO)
                        .collect(Collectors.toList()),
                entity.getCreatedAt().toInstant(),
                entity.getLastModifiedAt().toInstant()
        );
    }

    private WordDTO mapWordToDTO(WordEntity entity) {
        return new WordDTO(
                entity.getId(),
                entity.getOriginal(),
                entity.getTranslation(),
                entity.getDifficulty(),
                entity.getCreatedAt().toInstant(),
                entity.getLastModifiedAt().toInstant()
        );
    }
}
