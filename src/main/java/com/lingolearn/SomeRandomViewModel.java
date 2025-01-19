package com.lingolearn;

import java.util.ArrayList;
import java.util.List;

public class SomeRandomViewModel {
    private List<Subscriber> subscribers;
    private Object mainState; // could be whatever

    public void subscribe(Subscriber subscriber) {
        this.subscribers = new ArrayList<>();
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifySubscribers() {
        for (var subscriber : subscribers) {
            subscriber.update(this);
        }
    }

    public void mainBusinessLogic() {
        mainState = Integer.valueOf(5);
        notifySubscribers();
    }

    public Object getData() {
        return mainState;
    }
}
