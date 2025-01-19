package com.lingolearn.vocabulary.word.infra;

import com.lingolearn.vocabulary.word.domain.Word;

import java.util.ArrayList;
import java.util.List;

public final class WordMapper {
    private WordMapper() {
    } // Prevent instantiation

    public static Word toDomain(WordEntity entity) {
        if (entity == null) return null;

        return new Word(
                entity.getId(),
                entity.getOriginal(),
                entity.getTranslation(),
                entity.getPartOfSpeech(),
                new ArrayList<>(entity.getExampleSentences()), // Defensive copy
                entity.getDifficulty(),
                entity.getLastReviewed(),
                entity.getTimesReviewed(),
                entity.getCorrectAnswers()
        );
    }

    public static WordEntity toEntity(Word domain) {
        if (domain == null) return null;

        var entity = new WordEntity();
        entity.setId(domain.id());
        entity.setOriginal(domain.original());
        entity.setTranslation(domain.translation());
        entity.setPartOfSpeech(domain.partOfSpeech());
        entity.setExampleSentences(new ArrayList<>(domain.exampleSentences())); // Defensive copy
        entity.setDifficulty(domain.difficulty());
        entity.setLastReviewed(domain.lastReviewed());
        entity.setTimesReviewed(domain.timesReviewed());
        entity.setCorrectAnswers(domain.correctAnswers());

        return entity;
    }

    public static List<Word> toDomainList(List<WordEntity> entities) {
        if (entities == null) return List.of();
        return entities.stream()
                .map(WordMapper::toDomain)
                .toList();
    }

    public static List<WordEntity> toEntityList(List<Word> domains) {
        if (domains == null) return List.of();
        return domains.stream()
                .map(WordMapper::toEntity)
                .toList();
    }
}
