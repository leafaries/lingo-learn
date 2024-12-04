package com.lingolearnhub.learning.strategy.factories;

import com.lingolearnhub.learning.strategy.LearningStrategy;
import com.lingolearnhub.learning.strategy.MultipleChoiceStrategy;

/**
 * Klasa MultipleChoiceStrategyFactory rozszerza LearningStrategyFactory,
 * aby zapewnić konkretną implementację tworzenia instancji MultipleChoiceStrategy.
 *
 * Ta klasa jest odpowiedzialna za enkapsulację logiki tworzenia dla
 * MultipleChoiceStrategy, promując użycie wzorca Factory Method.
 * Dzięki temu podejściu pomaga w oddzieleniu kodu klienta od
 * szczegółów instancjonowania obiektów MultipleChoiceStrategy.
 */
public class MultipleChoiceStrategyFactory extends LearningStrategyFactory {
    /**
     * Tworzy i zwraca nową instancję MultipleChoiceStrategy.
     */
    @Override
    public LearningStrategy createStrategy() {
        return new MultipleChoiceStrategy();
    }
}
