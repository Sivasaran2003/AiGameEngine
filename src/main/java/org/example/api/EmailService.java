package org.example.api;

import org.example.commands.SendEmailCommand;

public class EmailService {
    void sendEmail(User user, String message) {
        // send mail
    }

    public void execute(SendEmailCommand command) {
        sendEmail(command.getReceiver(), command.getMessage());
    }
}