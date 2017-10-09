package com.acme.producer.service;

import com.codahale.metrics.Meter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class WidgetProducer {
  @Value("${kafka.topicName}")
  private String topicName;

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void loadAll() {
    //IntStream.range(1, 26).mapToObj(Integer::toString).forEach(WidgetProducer::loadRegion);
  }

  public void loadRegions(List<String> regionIds) {
    Meter meter = new Meter();
    regionIds.stream().forEach(regId -> loadRegion(meter, regId));
  }

  private static void loadRegion(Meter meter, String regionId) {
    String fileName = "resources/region_" + regionId + "_widgets.json";
    try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
      stream.forEach(System.out::println);
      meter.mark();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void sendMessage(String msg) {
    kafkaTemplate.send(topicName, msg);
  }
}
