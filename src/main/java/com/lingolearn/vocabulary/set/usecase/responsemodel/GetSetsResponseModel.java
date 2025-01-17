package com.lingolearn.vocabulary.set.usecase.responsemodel;

import com.lingolearn.vocabulary.set.domain.VocabularySet;

import java.util.List;

public record GetSetsResponseModel(
        List<VocabularySet> sets
) {
}
