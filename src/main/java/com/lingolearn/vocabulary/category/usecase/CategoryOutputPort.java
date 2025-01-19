package com.lingolearn.vocabulary.category.usecase;

public interface CategoryOutputPort {
    void presentCategory(CategoryResponseModel response);
    void presentHierarchy(CategoryHierarchyResponseModel response);
    void presentStatistics(CategoryStatisticsResponseModel response);
}
