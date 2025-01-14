package com.lingolearn.models;

import java.time.Instant;
import java.util.UUID;

public class Category {
    private final UUID id;
    private final Instant createdAt;
    private String name;
    private Instant lastModifiedAt;

    public Category(String name) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.name = name;
        this.lastModifiedAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.lastModifiedAt = Instant.now();
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getLastModifiedAt() {
        return lastModifiedAt;
    }
}
