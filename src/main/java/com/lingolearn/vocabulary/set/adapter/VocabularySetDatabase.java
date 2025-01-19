package com.lingolearn.vocabulary.set.adapter;

import com.lingolearn.vocabulary.set.domain.VocabularySet;

import java.util.List;
import java.util.Optional;

public interface VocabularySetDatabase {
    void save(VocabularySet set);
    Optional<VocabularySet> findById(Long id);
    List<VocabularySet> findAll();
    void deleteById(Long id);
}
