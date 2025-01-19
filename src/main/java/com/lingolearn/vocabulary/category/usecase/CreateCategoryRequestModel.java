package com.lingolearn.vocabulary.category.usecase;

public record CreateCategoryRequestModel(
        String name,
        String description,
        Long parentCategoryId
) {
}
