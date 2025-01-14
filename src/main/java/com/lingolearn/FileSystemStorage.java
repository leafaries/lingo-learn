package com.lingolearn;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lingolearn.enums.EntityType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class FileSystemStorage {
    private final Path basePath;
    private final ObjectMapper objectMapper;

    public FileSystemStorage(Path basePath) {
        this.basePath = basePath;
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        // Ensure storage directories exist for all entity types
        for (EntityType type : EntityType.values()) {
            try {
                Files.createDirectories(basePath.resolve(type.getStoragePath()));
            } catch (IOException e) {
                throw new RuntimeException("Failed to create storage directory for " + type, e);
            }
        }
    }

    public <T> void save(EntityType type, UUID id, T entity) {
        Path filePath = getFilePath(type, id);
        try {
            String json = objectMapper.writeValueAsString(entity);
            Files.writeString(filePath, json, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save entity: " + id, e);
        }
    }

    public <T> Optional<T> load(EntityType type, UUID id, Class<T> entityClass) {
        Path filePath = getFilePath(type, id);
        if (!Files.exists(filePath)) {
            return Optional.empty();
        }

        try {
            String json = Files.readString(filePath);
            return Optional.of(objectMapper.readValue(json, entityClass));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load entity: " + id, e);
        }
    }

    public <T> List<T> loadAll(EntityType type, Class<T> entityClass) {
        Path typePath = basePath.resolve(type.getStoragePath());
        try (Stream<Path> paths = Files.list(typePath)) {
            return paths
                    .filter(path -> path.toString().endsWith(".dat"))
                    .map(path -> {
                        try {
                            String json = Files.readString(path);
                            return objectMapper.readValue(json, entityClass);
                        } catch (IOException e) {
                            throw new RuntimeException("Failed to load entity from: " + path, e);
                        }
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load entities of type: " + type, e);
        }
    }

    public void delete(EntityType type, UUID id) {
        Path filePath = getFilePath(type, id);
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete entity: " + id, e);
        }
    }

    private Path getFilePath(EntityType type, UUID id) {
        return basePath.resolve(type.getStoragePath()).resolve(id + ".dat");
    }
}
