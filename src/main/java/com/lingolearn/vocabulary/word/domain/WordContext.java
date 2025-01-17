package com.lingolearn.vocabulary.word.domain;

import java.util.List;
import java.util.Map;

public record WordContext(
        Word word,
        List<String> collocations,
        List<String> commonPhrases,
        List<String> usageNotes,
        Map<String, String> regionalVariants
) {
}
