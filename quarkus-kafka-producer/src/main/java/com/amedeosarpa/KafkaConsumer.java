package com.amedeosarpa;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@Slf4j
@ApplicationScoped
public class KafkaConsumer{

    @Incoming("data-input")
    public void consume(ConsumerRecord<String, String> record){
        log.info("Received kafka message on topic {}", record.topic());
        log.info("kafka message key {}", record.key());
        log.info("kafka message value {}", record.value());
    }

    
}