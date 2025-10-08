package org.example.commands;

import org.example.api.User;

public class SendEmailCommand extends SendCommand{
     private String link;
     private String templateId;
     private String templateString;

     public SendEmailCommand(User receiver, String message, String link, String templateString, String templateId) {
          super(receiver, message);
          this.link = link;
          this.templateId = templateId;
          this.templateString = templateString;
     }
}
