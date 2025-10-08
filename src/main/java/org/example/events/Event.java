package org.example.events;

import org.example.api.User;

public class Event {
    private User receiver;
    private String message;
    private String link;
    private String templateId;
    private String templateString;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

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

    public String getTemplateId() {
        return templateId;
    }

    public Event(User receiver, String message, String link) {
        this.receiver = receiver;
        this.message = message;
        this.link = link;
    }

    public Event(User receiver, String message, String link, String templateId, String templateString) {
        this.receiver = receiver;
        this.message = message;
        this.link = link;
        this.templateId = templateId;
        this.templateString = templateString;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateString() {
        return templateString;
    }

    public void setTemplateString(String templateString) {
        this.templateString = templateString;
    }
}
