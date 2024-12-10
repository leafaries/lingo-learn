package com.lingolearnhub.learning.strategy.factories;

import com.lingolearnhub.learning.strategy.LearningStrategy;

/**
 * Klasa LearningStrategyFactory jest odpowiedzialna za tworzenie instancji
 * {@link LearningStrategy}. Ta abstrakcyjna fabryka zapewnia metodę dla klientów
 * do żądania konkretnego typu strategii uczenia bez potrzeby znajomości
 * szczegółów tworzenia.
 *
 * Podklasy powinny zdefiniować konkretną logikę tworzenia strategii
 * w swojej implementacji metody {@link #createStrategy()}. To sprzyja
 * przestrzeganiu zasady Otwarte/Zamknięte, pozwalając na dostosowanie
 * tworzenia strategii poprzez dziedziczenie.
 */
public abstract class LearningStrategyFactory {

    public abstract LearningStrategy createStrategy();

}
