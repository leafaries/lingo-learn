package com.lingolearn.vocabulary.word.infra;

import com.lingolearn.vocabulary.word.domain.Word;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;

@Mapper(componentModel = "default")
public interface WordMapper {
    Word toDomain(WordEntity entity);
    WordEntity toEntity(Word domain);

    @AfterMapping
    default void mapExamples(Word domain, @MappingTarget WordEntity entity) {
        if (domain.exampleSentences() != null) {
            entity.setExampleSentences(new ArrayList<>(domain.exampleSentences()));
        }
    }
}
