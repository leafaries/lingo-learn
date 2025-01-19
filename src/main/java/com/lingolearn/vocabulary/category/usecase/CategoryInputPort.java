package com.lingolearn.vocabulary.category.usecase;

public interface CategoryInputPort {
    void createCategory(CreateCategoryRequestModel request);
    void updateCategory(UpdateCategoryRequestModel request);
    void assignSetsToCategory(AssignSetsToCategoryRequestModel request);
    void getCategoryHierarchy();
    void getCategoryStatistics(Long categoryId);
}
