package org.example.commands.builders;

import org.example.api.User;
import org.example.commands.SendEmailCommand;
import org.example.commands.SendSMSCommand;

public class SendSMSCommandBuilder {
    private final SendBuilder sendBuilder = new SendBuilder();
    private String link;
    private String templateId;
    private String templateString;

    public SendSMSCommandBuilder() {}

    public SendSMSCommandBuilder user(User user) {
        sendBuilder.setReceiver(user);
        return this;
    }

    public SendSMSCommandBuilder message(String message) {
        sendBuilder.message(message);
        return this;
    }

    public SendSMSCommandBuilder link(String link) {
        this.link = link;
        return this;
    }

    public SendSMSCommandBuilder templateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public SendSMSCommandBuilder templateString(String templateId) {
        this.templateString = templateString;
        return this;
    }

    public SendSMSCommand build() {
        return new SendSMSCommand(sendBuilder.getReceiver(), sendBuilder.getMessage(), link, templateId, templateString);
    }
}
