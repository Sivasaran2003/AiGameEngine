package org.example.commands.builders;

import org.example.api.User;
import org.example.commands.SendCommand;

public class SendBuilder {
    private User receiver;
    private String message;

    public SendBuilder() {}

    public SendBuilder(User user, String message) {
        this.receiver = user;
        this.message = message;
    }

    public SendBuilder receiver(User user) {
        this.receiver = user;
        return this;
    }

    public SendBuilder message(String message) {
        this.message = message;
        return this;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SendCommand build() {
        return new SendCommand(receiver, message);
    }
}
