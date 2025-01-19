package com.lingolearn.vocabulary.word.adapter;

public interface Observable<T> {
    void addObserver(Observer<T> observer);
    void removeObserver(Observer<T> observer);
    void notifyObservers();
}
