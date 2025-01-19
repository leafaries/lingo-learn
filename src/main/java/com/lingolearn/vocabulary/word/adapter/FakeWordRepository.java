package com.lingolearn.vocabulary.word.adapter;

import com.lingolearn.vocabulary.word.domain.Difficulty;
import com.lingolearn.vocabulary.word.domain.Word;
import com.lingolearn.vocabulary.word.domain.WordNotFoundException;
import com.lingolearn.vocabulary.word.usecase.WordRepository;

import java.util.*;

public class FakeWordRepository implements WordRepository {
    private final Map<Long, Word> words = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public Word save(Word word) {
        if (word.id() == null) {
            word = new Word(
                    nextId++,
                    word.original(),
                    word.translation(),
                    word.partOfSpeech(),
                    word.exampleSentences(),
                    word.difficulty(),
                    word.lastReviewed(),
                    word.timesReviewed(),
                    word.correctAnswers()
            );
        }
        words.put(word.id(), word);
        return word;
    }

    @Override
    public Optional<Word> findById(Long id) {
        return Optional.ofNullable(words.get(id));
    }

    @Override
    public List<Word> findAll() {
        return new ArrayList<>(words.values());
    }

    @Override
    public void deleteById(Long id) {
        words.remove(id);
    }

    @Override
    public void deleteByIdOrThrow(Long id) throws WordNotFoundException {
        if (!words.containsKey(id)) {
            throw new WordNotFoundException(id);
        }
        words.remove(id);
    }

    @Override
    public List<Word> findByDifficulty(Difficulty difficulty) {
        return words.values().stream()
                .filter(word -> word.difficulty() == difficulty)
                .toList();
    }

    @Override
    public Optional<Word> findByOriginalAndTranslation(String original, String translation) {
        return words.values().stream()
                .filter(word -> word.original().equals(original)
                        && word.translation().equals(translation))
                .findFirst();
    }

    public void clear() {
        words.clear();
        nextId = 1L;
    }
}
