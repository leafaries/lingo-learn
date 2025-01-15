package com.lingolearn.core.vocabulary;

import com.lingolearn.core.LingoLearn;
import com.lingolearn.dtos.CategoryDTO;
import com.lingolearn.dtos.VocabularySetDTO;
import com.lingolearn.entities.CategoryEntity;
import com.lingolearn.entities.VocabularySetEntity;
import com.lingolearn.repo.CategoryRepository;
import com.lingolearn.repo.VocabularySetRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CategoryManagerImpl implements LingoLearn.VocabularyManager.CategoryManager {
    private final CategoryRepository categoryRepository;
    private final VocabularySetRepository setRepository;

    public CategoryManagerImpl() {
        this.categoryRepository = new CategoryRepository();
        this.setRepository = new VocabularySetRepository();
    }

    @Override
    public CategoryDTO create(String name) {
        CategoryEntity entity = new CategoryEntity();
        entity.setName(name);
        entity = categoryRepository.save(entity);
        return mapToDTO(entity);
    }

    @Override
    public List<CategoryDTO> getAll() {
        return categoryRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(CategoryDTO category, String name) {
        categoryRepository.findById(category.id()).ifPresent(entity -> {
            entity.setName(name);
            categoryRepository.save(entity);
        });
    }

    @Override
    public void assignToSet(CategoryDTO category, VocabularySetDTO set) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(category.id());
        Optional<VocabularySetEntity> setEntity = setRepository.findById(set.id());

        if (categoryEntity.isPresent() && setEntity.isPresent()) {
            setEntity.get().setCategory(categoryEntity.get());
            setRepository.save(setEntity.get());
        }
    }

    @Override
    public void removeFromSet(CategoryDTO category, VocabularySetDTO set) {
        setRepository.findById(set.id()).ifPresent(entity -> {
            entity.setCategory(null);
            setRepository.save(entity);
        });
    }

    @Override
    public void delete(CategoryDTO category) {
        categoryRepository.findById(category.id()).ifPresent(categoryRepository::delete);
    }

    private CategoryDTO mapToDTO(CategoryEntity entity) {
        return new CategoryDTO(
                entity.getName(),
                entity.getCreatedAt().toInstant(),
                entity.getLastModifiedAt().toInstant()
        );
    }
}
