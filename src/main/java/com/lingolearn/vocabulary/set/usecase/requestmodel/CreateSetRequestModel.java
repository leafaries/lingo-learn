package com.lingolearn.vocabulary.set.usecase.requestmodel;

import java.util.List;

public record CreateSetRequestModel(
        String name,
        String description,
        Long categoryId,
        int targetDailyWords,
        List<Long> wordIds
) {
}
