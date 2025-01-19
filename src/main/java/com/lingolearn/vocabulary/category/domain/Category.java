package com.lingolearn.vocabulary.category.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public record Category(
        Long id,
        String name,
        String description,
        Long parentCategoryId,
        Set<Long> childCategoryIds,
        Set<Long> vocabularySetIds,
        LocalDateTime createdAt,
        LocalDateTime lastModifiedAt
) {
    private static final int MAX_NAME_LENGTH = 100;
    private static final int MAX_DESCRIPTION_LENGTH = 500;
    private static final int MAX_NESTING_LEVEL = 5;

    public Category {
        validateName(name);
        validateDescription(description);

        // Defensive copies for mutable collections
        childCategoryIds = Set.copyOf(childCategoryIds);
        vocabularySetIds = Set.copyOf(vocabularySetIds);
    }

    // Factory method for new categories
    public static Category create(String name, String description, Long parentCategoryId) {
        return new Category(
                null,
                name,
                description,
                parentCategoryId,
                Set.of(),
                Set.of(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    // Validation methods
    private static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    "Category name cannot exceed " + MAX_NAME_LENGTH + " characters"
            );
        }
    }

    private static void validateDescription(String description) {
        if (description != null && description.length() > MAX_DESCRIPTION_LENGTH) {
            throw new IllegalArgumentException(
                    "Category description cannot exceed " + MAX_DESCRIPTION_LENGTH + " characters"
            );
        }
    }

    // Business methods returning new instances
    public Category withUpdatedDetails(String newName, String newDescription) {
        return new Category(
                id,
                newName,
                newDescription,
                parentCategoryId,
                childCategoryIds,
                vocabularySetIds,
                createdAt,
                LocalDateTime.now()
        );
    }

    public Category addChild(Long childId) {
        validateChildAddition(childId);
        var newChildren = new HashSet<>(childCategoryIds);
        newChildren.add(childId);

        return new Category(
                id,
                name,
                description,
                parentCategoryId,
                newChildren,
                vocabularySetIds,
                createdAt,
                LocalDateTime.now()
        );
    }

    public Category removeChild(Long childId) {
        if (!childCategoryIds.contains(childId)) {
            throw new CategoryNotFoundException(childId);
        }

        var newChildren = new HashSet<>(childCategoryIds);
        newChildren.remove(childId);

        return new Category(
                id,
                name,
                description,
                parentCategoryId,
                newChildren,
                vocabularySetIds,
                createdAt,
                LocalDateTime.now()
        );
    }

    public Category addVocabularySet(Long setId) {
        var newSets = new HashSet<>(vocabularySetIds);
        newSets.add(setId);

        return new Category(
                id,
                name,
                description,
                parentCategoryId,
                childCategoryIds,
                newSets,
                createdAt,
                LocalDateTime.now()
        );
    }

    public Category removeVocabularySet(Long setId) {
        if (!vocabularySetIds.contains(setId)) {
            throw new IllegalArgumentException("Vocabulary set not found: " + setId);
        }

        var newSets = new HashSet<>(vocabularySetIds);
        newSets.remove(setId);

        return new Category(
                id,
                name,
                description,
                parentCategoryId,
                childCategoryIds,
                newSets,
                createdAt,
                LocalDateTime.now()
        );
    }

    // Query methods
    public boolean isRoot() {
        return parentCategoryId == null;
    }

    public boolean hasChildren() {
        return !childCategoryIds.isEmpty();
    }

    public boolean containsVocabularySet(Long setId) {
        return vocabularySetIds.contains(setId);
    }

    public int vocabularySetCount() {
        return vocabularySetIds.size();
    }

    private void validateChildAddition(Long childId) {
        if (childId == null) {
            throw new IllegalArgumentException("Child category ID cannot be null");
        }
        if (childCategoryIds.contains(childId)) {
            throw new IllegalArgumentException("Category already contains child: " + childId);
        }
        if (id != null && id.equals(childId)) {
            throw new IllegalArgumentException("Category cannot be its own child");
        }
    }
}
