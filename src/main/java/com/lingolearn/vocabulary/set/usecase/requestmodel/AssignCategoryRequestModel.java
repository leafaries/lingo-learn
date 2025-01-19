package com.lingolearn.vocabulary.set.usecase.requestmodel;

public record AssignCategoryRequestModel(
        Long setId,
        Long categoryId
) {
}
