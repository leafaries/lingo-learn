package com.lingolearn.vocabulary.category.usecase;

import com.lingolearn.vocabulary.category.domain.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryInputPort {
    CategoryResponseModel createCategory(CreateCategoryRequestModel request);
    CategoryResponseModel updateCategory(UpdateCategoryRequestModel request);
    List<Category> getCategoryHierarchy();
    CategoryStatistics getCategoryStatistics(UUID categoryId);
}
