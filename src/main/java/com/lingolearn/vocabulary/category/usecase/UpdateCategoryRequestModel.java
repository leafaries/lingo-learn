package com.lingolearn.vocabulary.category.usecase;

public record UpdateCategoryRequestModel(
        Long categoryId,
        String name,
        String description,
        Long parentCategoryId
) {
}
