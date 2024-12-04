package com.lingolearnhub.learning.strategy.factories;

import com.lingolearnhub.learning.strategy.LearningStrategy;
import com.lingolearnhub.learning.strategy.TranslationInputStrategy;

/**
 * Klasa TranslationInputStrategyFactory rozszerza LearningStrategyFactory,
 * aby zapewnić konkretną implementację tworzenia instancji TranslationInputStrategy.
 *
 * Ta klasa jest odpowiedzialna za enkapsulację logiki tworzenia dla
 * TranslationInputStrategy, promując użycie wzorca Factory Method.
 * Dzięki temu podejściu pomaga w oddzieleniu kodu klienta od
 * szczegółów instancjonowania obiektów TranslationInputStrategy.
 */
public class TranslationInputStrategyFactory extends LearningStrategyFactory {
    /**
     * Tworzy i zwraca nową instancję TranslationInputStrategy.
     */
    @Override
    public LearningStrategy createStrategy() {
        return new TranslationInputStrategy();
    }
}
