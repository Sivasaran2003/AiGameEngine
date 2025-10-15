package org.example.commands;

import org.example.events.Event;

public class SendEmailCommand extends SendCommand{
     private String link;
     private String templateId;
     private String templateString;

     public SendEmailCommand(Event event) {
          super(event.getReceiver(), event.getMessage());
          this.link = event.getLink();
          this.templateId = event.getTemplateId();
          this.templateString = event.getTemplateString();
     }
}
