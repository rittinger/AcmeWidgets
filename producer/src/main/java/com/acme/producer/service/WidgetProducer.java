package com.acme.producer.service;

import com.acme.producer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WidgetProducer {
  
  @Autowired
  private MessageService messageService;

  public void test(){
    messageService.sendMessage("widget","this is a test");
  }
}
