package com.acme.producer;

import com.acme.producer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WidgetProducer {
  @Value("${kafka.boostrapAddress}")
  private String boostrapAddress;
  
  @Autowired
  private MessageService messageService;

  public void test(){
    messageService.sendMessage("test","this is a test");
  }
}
