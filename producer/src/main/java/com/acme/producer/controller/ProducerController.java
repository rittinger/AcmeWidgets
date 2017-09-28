package com.acme.producer.controller;

import com.acme.producer.service.WidgetProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
  @Autowired
  private WidgetProducer producer;

  @RequestMapping("/test")
  public String test(){
    producer.test();
    return "success!";
  }
}
