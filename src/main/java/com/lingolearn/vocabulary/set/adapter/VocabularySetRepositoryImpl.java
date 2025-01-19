package com.lingolearn.vocabulary.set.adapter;

import com.lingolearn.NotImplementedException;
import com.lingolearn.vocabulary.set.domain.VocabularySet;
import com.lingolearn.vocabulary.set.infra.JpaVocabularySetDatabaseImpl;
import com.lingolearn.vocabulary.set.usecase.VocabularySetRepository;

import java.util.List;
import java.util.Optional;

public class VocabularySetRepositoryImpl implements VocabularySetRepository {
    private final JpaVocabularySetDatabaseImpl repository;

    public VocabularySetRepositoryImpl(JpaVocabularySetDatabaseImpl repository) {
        this.repository = repository;
    }

    @Override
    public void save(VocabularySet set) {
        // TODO: Impl
        throw new NotImplementedException();
    }

    @Override
    public Optional<VocabularySet> findById(Long id) {
        // TODO: Impl
        throw new NotImplementedException();
    }

    @Override
    public List<VocabularySet> findAll() {
        // TODO: Impl
        throw new NotImplementedException();
    }

    @Override
    public void deleteById(Long id) {
        // TODO: Impl
        throw new NotImplementedException();
    }
}
