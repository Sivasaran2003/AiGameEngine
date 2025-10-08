package org.example.commands.builders;

import org.example.api.User;
import org.example.commands.SendCommand;
import org.example.commands.SendEmailCommand;

public class SendEmailCommandBuilder {
    private final SendBuilder sendBuilder = new SendBuilder();
    private String link;
    private String templateId;
    private String templateString;

    public SendEmailCommandBuilder user(User user) {
        sendBuilder.setReceiver(user);
        return this;
    }

    public SendEmailCommandBuilder message(String message) {
        sendBuilder.setMessage(message);
        return this;
    }

    public SendEmailCommandBuilder link(String link) {
        this.link = link;
        return this;
    }

    public SendEmailCommandBuilder templateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public SendEmailCommandBuilder templateString(String templateId) {
        this.templateString = templateString;
        return this;
    }

    public SendEmailCommand build() {
        return new SendEmailCommand(sendBuilder.getReceiver(), sendBuilder.getMessage(), link, templateId, templateString);
    }
}
