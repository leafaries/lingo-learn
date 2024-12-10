package com.lingolearnhub.learning.strategy.factories;

import com.lingolearnhub.learning.strategy.FlashcardStrategy;
import com.lingolearnhub.learning.strategy.LearningStrategy;

/**
 * Klasa FlashcardStrategyFactory rozszerza LearningStrategyFactory,
 * aby zapewnić konkretną implementację tworzenia instancji FlashcardStrategy.
 *
 * Ta klasa jest odpowiedzialna za enkapsulację logiki tworzenia dla
 * FlashcardStrategy, promując użycie wzorca Factory Method.
 * Dzięki temu podejściu pomaga w oddzieleniu kodu klienta od
 * szczegółów instancjonowania obiektów FlashcardStrategy.
 */
public class FlashcardStrategyFactory extends LearningStrategyFactory {

    /**
     * Tworzy i zwraca nową instancję FlashcardStrategy.
     */
    @Override
    public LearningStrategy createStrategy() {
        return new FlashcardStrategy();
    }

}
