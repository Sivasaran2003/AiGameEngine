package org.example.commands;

import org.example.api.User;

public class SendEmailCommand {
     private User receiver;
     private String message;
     private String link;
     private String templateId;
     private String templateString;

     public SendEmailCommand(User receiver, String message, String link, String templateString, String templateId) {
          this.receiver = receiver;
          this.message = message;
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
