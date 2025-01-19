package com.lingolearn.vocabulary.category.usecase;

import com.lingolearn.vocabulary.category.domain.CategoryHierarchyNode;
import com.lingolearn.vocabulary.category.domain.CategoryStatistics;
import com.lingolearn.vocabulary.category.domain.VocabularySetInfo;

import java.util.List;

record CategoryResponseModel(
        Long id,
        String name,
        String description,
        CategoryHierarchyNode hierarchy,
        List<VocabularySetInfo> sets,
        CategoryStatistics statistics
) {
}
