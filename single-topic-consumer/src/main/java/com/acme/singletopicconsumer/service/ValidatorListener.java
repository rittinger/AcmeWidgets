package com.acme.singletopicconsumer.service;

import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;

/**
 * Created by dd15475 on 9/15/17.
 */
public class ValidatorListener {

    @KafkaListener(topics = "${message.topic.name}", containerFactory = "validatorKafkaListenerContainerFactory")
    public void listenGroupFoo(List<String> messages) {
        messages.stream().forEach(System.out::println);
        //System.out.println("Received Messasge in group 'validator': " + message);
    }

}
