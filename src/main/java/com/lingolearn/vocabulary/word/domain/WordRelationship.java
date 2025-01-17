package com.lingolearn.vocabulary.word.domain;

public record WordRelationship(
        Word sourceWord,
        Word targetWord,
        RelationType type
) {
}
