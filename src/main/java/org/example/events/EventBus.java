package org.example.events;

import java.util.ArrayList;
import java.util.List;

public class EventBus {
    private final List<Event> events;
    private final List<Subscriber> subscribers;

    public EventBus() {
        events = new ArrayList<>();
        subscribers = new ArrayList<>();
    }

    public void publish(Event event) {
        events.add(event);
    }

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }
}
