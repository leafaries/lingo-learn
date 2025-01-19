package com.lingolearn.vocabulary.set.usecase.requestmodel;

public record UpdateSetRequestModel(
        Long id,
        String name,
        String description,
        int targetDailyWords
) {
}
