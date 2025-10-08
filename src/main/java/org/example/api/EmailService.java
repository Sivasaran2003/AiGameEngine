package org.example.api;

import org.example.commands.SendEmailCommand;

public class EmailService {
    void sendEmail(User user, String message) {
        // send mail
    }

    public Void execute(SendEmailCommand command) {
        sendEmail(command.getReceiver(), command.getMessage());
        return null;
    }
}