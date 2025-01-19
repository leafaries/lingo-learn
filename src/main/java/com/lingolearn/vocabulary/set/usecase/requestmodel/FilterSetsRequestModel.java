package com.lingolearn.vocabulary.set.usecase.requestmodel;

import com.lingolearn.vocabulary.word.domain.Difficulty;

import java.time.LocalDateTime;
import java.util.List;

public record FilterSetsRequestModel(
        String searchTerm,
        List<Long> categoryIds,
        List<Difficulty> difficulties,
        Boolean isActive,
        LocalDateTime studiedAfter,
        LocalDateTime studiedBefore,
        Double minProgress
) {
}
