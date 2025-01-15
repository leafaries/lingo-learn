package com.lingolearn.core.vocabulary;

import com.lingolearn.core.LingoLearn;
import com.lingolearn.dtos.CategoryDTO;
import com.lingolearn.models.Category;
import com.lingolearn.models.VocabularySet;

import java.util.List;

public class CategoryManagerImpl implements LingoLearn.VocabularyManager.CategoryManager {
    @Override
    public CategoryDTO create(String name) {
        return null;
    }

    @Override
    public List<CategoryDTO> getAll() {
        return List.of();
    }

    @Override
    public void update(CategoryDTO category, String name) {

    }

    @Override
    public void assignToSet(Category category, VocabularySet set) {

    }

    @Override
    public void removeFromSet(Category category, VocabularySet set) {

    }

    @Override
    public void delete(Category category) {

    }
}
