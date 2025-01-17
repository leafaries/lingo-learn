package com.lingolearn.vocabulary.set.infra;

import com.lingolearn.vocabulary.set.domain.VocabularySet;
import com.lingolearn.vocabulary.word.domain.Word;
import horizontallayers.infrastructure.jpaentities.WordEntity;
import org.mapstruct.*;

@Mapper(componentModel = "default",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface VocabularySetMapper {
    VocabularySetEntity toEntity(VocabularySet domain);
    VocabularySet toDomain(VocabularySetEntity entity);

    WordEntity toEntity(Word domain);
    Word toDomain(WordEntity entity);

//    WordGroupEntity toEntity(WordGroup domain);
//    WordGroup toDomain(WordGroupEntity entity);

    @Mapping(target = "items", ignore = true)
        // Handle items separately due to composite pattern
    WordGroupEntity toEntityWithoutItems(WordGroup domain);

    @AfterMapping
    default void mapItems(WordGroup domain, @MappingTarget WordGroupEntity entity) {
        if (domain.getItems() != null) {
            entity.setItems(domain.getItems().stream()
                    .map(item -> {
                        if (item instanceof Word) {
                            return toEntity((Word) item);
                        } else {
                            return toEntity((WordGroup) item);
                        }
                    })
                    .toList());
        }
    }
}
