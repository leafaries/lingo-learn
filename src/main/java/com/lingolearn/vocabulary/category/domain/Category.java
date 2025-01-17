package com.lingolearn.vocabulary.category.domain;

import java.time.LocalDateTime;
import java.util.Set;

public record Category(
        Long id,
        String name,
        String description,
        Long parentCategoryId,
        Set<Long> childCategoryIds,
        LocalDateTime createdAt
) {
    public boolean isRoot() {
        return parentCategoryId == null;
    }
}
