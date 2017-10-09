package com.acme.producer.controller;

import com.acme.producer.service.WidgetProducer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class ProducerController {
  @Autowired
  private WidgetProducer producer;

  @RequestMapping("/loadAllRegions")
  public void loadAllRegions(){
    producer.loadAll();
  }

  @RequestMapping("/loadRegions")
  public void loadRegions(@RequestParam List<String> regionIds){
    producer.loadRegions(regionIds);
  }
}
