package org.example.events;

import org.example.api.User;

public class WinEvent extends Event{

    public WinEvent(User user) {
        super(user, "Congrats on the Win !!", "www.tic-tac-toe.com", null, null);
    }
}
