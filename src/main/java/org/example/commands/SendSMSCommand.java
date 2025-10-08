package org.example.commands;

import org.example.api.User;

public class SendSMSCommand extends SendCommand{
    private String link;
    private String templateId;
    private String templateString;

    public SendSMSCommand(User receiver, String message, String link, String templateString, String templateId) {
        super(receiver, message);
        this.link = link;
        this.templateId = templateId;
        this.templateString = templateString;
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
