package org.example.commands;

import org.example.api.User;

public class SendCommand {
    private User receiver;
    private String message;

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SendCommand(User receiver, String message) {
        this.receiver = receiver;
        this.message = message;
    }
}
