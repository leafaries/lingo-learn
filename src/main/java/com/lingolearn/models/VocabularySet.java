package com.lingolearn.models;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class VocabularySet implements LearningItem {
    private final Set<LearningItem> items;
    private final Instant createdAt;

    private String name;
    private String description;
    private UUID categoryId;
    private Instant lastModifiedAt;

    public VocabularySet(String name, String description) {
        this.name = name;
        this.description = description;
        this.items = new HashSet<>();
        this.createdAt = Instant.now();
        this.lastModifiedAt = createdAt;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        updateLastModified();
    }

    @Override
    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public Instant getLastModifiedAt() {
        return lastModifiedAt;
    }

    @Override
    public Set<UUID> getAllWordIds() {
        return items.stream()
                .flatMap(item -> item.getAllWordIds().stream())
                .collect(Collectors.toUnmodifiableSet());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        updateLastModified();
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
        updateLastModified();
    }

    public void addItem(LearningItem item) {
        items.add(item);
        updateLastModified();
    }

    public void removeItem(LearningItem item) {
        items.remove(item);
        updateLastModified();
    }

    public Set<LearningItem> getItems() {
        return Collections.unmodifiableSet(items);
    }

    private void updateLastModified() {
        this.lastModifiedAt = Instant.now();
    }
}
