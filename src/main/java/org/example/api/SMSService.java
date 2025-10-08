package org.example.api;

import org.example.commands.SendSMSCommand;

public class SMSService {
    public void sendSMS(User user, String message) {
        //todo:logic for sending sms
    }

    public Void execute(SendSMSCommand command) {
        sendSMS(command.getReceiver(), command.getMessage());
        return null;
    }
}
