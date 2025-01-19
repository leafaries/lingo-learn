package com.lingolearn.vocabulary.category.usecase;

import com.lingolearn.common.domain.NotImplementedException;
import com.lingolearn.vocabulary.set.usecase.VocabularySetRepository;

class CategoryInteractor implements CategoryInputPort {
    private final CategoryOutputPort outputPort;
    private final CategoryRepository repository;
    private final VocabularySetRepository setRepository;

    public CategoryInteractor(
            CategoryOutputPort outputPort,
            CategoryRepository repository,
            VocabularySetRepository setRepository
    ) {
        this.outputPort = outputPort;
        this.repository = repository;
        this.setRepository = setRepository;
    }

    @Override
    public void createCategory(CreateCategoryRequestModel request) {
        // TODO: Impl
        throw new NotImplementedException();
        // Validate parent category if exists
        // Create new category
        // Update hierarchy
        // Save to repository
        // Present result
    }

    @Override
    public void updateCategory(UpdateCategoryRequestModel request) {
        // TODO: Impl
        throw new NotImplementedException();
        // Validate changes
        // Update category
        // Update hierarchy if needed
        // Save to repository
        // Present result
    }

    @Override
    public void assignSetsToCategory(AssignSetsToCategoryRequestModel request) {
        // TODO: Impl
        throw new NotImplementedException();
        // Validate category and sets
        // Update associations
        // Save to repository
        // Present updated category
    }

    @Override
    public void getCategoryHierarchy() {
        // TODO: Impl
        throw new NotImplementedException();
        // Retrieve all categories
        // Build hierarchy tree
        // Present hierarchy
    }

    @Override
    public void getCategoryStatistics(Long categoryId) {
        // TODO: Impl
        throw new NotImplementedException();
        // Calculate statistics
        // Include subcategories
        // Present statistics
    }
}
