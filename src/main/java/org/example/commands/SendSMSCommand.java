package org.example.commands;

import org.example.api.User;
import org.example.events.Event;

public class SendSMSCommand extends SendCommand{
    private String link;
    private String templateId;
    private String templateString;

    public SendSMSCommand(Event event) {
        super(event.getReceiver(), event.getMessage());
        this.link = event.getLink();
        this.templateId = event.getTemplateId();
        this.templateString = event.getTemplateString();
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTemplateString() {
        return templateString;
    }

    public void setTemplateString(String templateString) {
        this.templateString = templateString;
    }
}
