package com.lingolearn.vocabulary.category.domain;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record Category(
        UUID id,
        String name,
        String description,
        UUID parentCategoryId,
        Set<UUID> childCategoryIds,
        LocalDateTime createdAt
) {
    public boolean isRoot() {
        return parentCategoryId == null;
    }
}
