package com.lingolearn.vocabulary.set.usecase;

import com.lingolearn.vocabulary.set.domain.VocabularySet;

import java.util.List;
import java.util.Optional;

public interface VocabularySetRepository {
    void save(VocabularySet set);
    Optional<VocabularySet> findById(Long id);
    List<VocabularySet> findAll();
    void deleteById(Long id);
}
