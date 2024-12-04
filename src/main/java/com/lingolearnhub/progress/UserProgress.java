package com.lingolearnhub.progress;

/**
 * Śledzi postępy użytkownika.
 */
public class UserProgress { // FIXME: Clean from bad observer pattern
    private int correctAnswers;
    private int totalAnswers;
//    private final List<ProgressObserver> observers = new ArrayList<>();

    public void incrementCorrect() {
        correctAnswers++;
//        notifyObservers();
    }

    public void incrementTotal() {
        totalAnswers++;
//        notifyObservers();
    }

    public void updateProgress() {
        // TODO: Implement
    }

    public double getProgress() {
        // Implementation omitted for brevity
        // Example: return correctAnswers / (double) totalAnswers;
        return 0.0; // Replace with actual calculation
    }

//    public void addObserver(ProgressObserver observer) {
//        observers.add(observer);
//    }
//
//    public void removeObserver(ProgressObserver observer) {
//        observers.remove(observer);
//    }
//
//    private void notifyObservers() {
//        for (ProgressObserver observer : observers) {
//            observer.updateProgress(getProgress());
//        }
//    }
}
