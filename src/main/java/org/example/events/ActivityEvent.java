package org.example.events;

import org.example.api.User;

public class ActivityEvent extends Event{
    public ActivityEvent(User user) {
        super(user, "Welcome Back !!", "www.tic-tac-toe.com", null, null);
    }
}
