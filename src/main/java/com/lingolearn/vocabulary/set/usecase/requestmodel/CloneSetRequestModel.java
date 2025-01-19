package com.lingolearn.vocabulary.set.usecase.requestmodel;

public record CloneSetRequestModel(
        Long sourceSetId,
        String newName,
        Long categoryId
) {
}
