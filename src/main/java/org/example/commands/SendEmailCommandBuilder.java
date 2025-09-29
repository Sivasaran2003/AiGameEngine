package org.example.commands;

import org.example.api.User;

public class SendEmailCommandBuilder {
    private User receiver;
    private String message;
    private String link;
    private String templateId;
    private String templateString;

    public SendEmailCommandBuilder receiver(User receiver) {
        this.receiver = receiver;
        return this;
    }

    public SendEmailCommandBuilder message(String message) {
        this.message = message;
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
        return new SendEmailCommand(receiver, message, link, templateId, templateString);
    }
}
