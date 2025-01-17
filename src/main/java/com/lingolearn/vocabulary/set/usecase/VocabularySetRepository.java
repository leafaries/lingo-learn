package com.lingolearn.vocabulary.set.usecase;

import com.lingolearn.vocabulary.set.domain.VocabularySet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VocabularySetRepository {
    VocabularySet save(VocabularySet set);
    Optional<VocabularySet> findById(UUID id);
    List<VocabularySet> findAll();
    void deleteById(UUID id);
    void assignCategory(UUID setId, UUID categoryId);
}
