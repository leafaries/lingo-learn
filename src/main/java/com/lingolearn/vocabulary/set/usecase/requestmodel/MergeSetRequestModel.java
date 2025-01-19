package com.lingolearn.vocabulary.set.usecase.requestmodel;

import java.util.List;

public record MergeSetRequestModel(
        Long targetSetId,
        List<Long> sourceSetIds,
        boolean keepSourceSets
) {
}
