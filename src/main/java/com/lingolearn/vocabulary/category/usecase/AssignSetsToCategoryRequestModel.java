package com.lingolearn.vocabulary.category.usecase;

import java.util.List;

record AssignSetsToCategoryRequestModel(
        Long categoryId,
        List<Long> vocabularySetIds
) {
}
