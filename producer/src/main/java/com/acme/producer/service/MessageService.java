package com.acme.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageService {
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(String topicName, String msg) {
    kafkaTemplate.send(topicName, msg);
  }
}
