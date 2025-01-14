package com.lingolearn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Generator of unique UUIDs */
public final class FileBasedIdGenerator {
    private final Path basePath;
    private final Lock lock = new ReentrantLock();

    public FileBasedIdGenerator(Path basePath) {
        this.basePath = basePath;
        // Ensure storage directories exist for all entity types
        for (EntityType type : EntityType.values()) {
            try {
                Files.createDirectories(basePath.resolve(type.getStoragePath()));
            } catch (IOException e) {
                throw new RuntimeException("Failed to create storage directory for " + type, e);
            }
        }
    }

    public UUID generateUniqueId(EntityType type) {
        lock.lock();
        try {
            Path typePath = basePath.resolve(type.name().toLowerCase());
            Set<UUID> existingIds;

            try (Stream<Path> paths = Files.list(typePath)) {
                existingIds = paths
                        .map(path -> path.getFileName().toString())
                        .map(name -> name.replace(".dat", ""))
                        .map(UUID::fromString)
                        .collect(Collectors.toSet());
            }

            UUID id;
            do {
                id = UUID.randomUUID();
            } while (existingIds.contains(id));

            return id;
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate unique ID", e);
        } finally {
            lock.unlock();
        }
    }
}
