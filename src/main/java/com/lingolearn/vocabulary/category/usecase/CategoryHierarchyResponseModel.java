package com.lingolearn.vocabulary.category.usecase;

import com.lingolearn.vocabulary.category.domain.CategoryHierarchyNode;

import java.util.List;

record CategoryHierarchyResponseModel(
        List<CategoryHierarchyNode> rootCategories
) {
}
