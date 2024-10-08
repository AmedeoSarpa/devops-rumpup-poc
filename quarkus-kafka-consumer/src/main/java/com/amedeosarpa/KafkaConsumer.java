package com.amedeosarpa;

import com.amedeosarpa.dto.MeasurementRecord;
import com.amedeosarpa.mongo.MongoException;
import com.amedeosarpa.mongo.MongoImplementation;
import com.mongodb.MongoClientException;
import com.mongodb.MongoInternalException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@Slf4j
@ApplicationScoped
public class KafkaConsumer {

    @Inject
    MongoImplementation mongoImplementation;

    @Incoming("data-output")
    public void consume(ConsumerRecord<String, MeasurementRecord> record){
        log.info("Received kafka message on topic {}", record.topic());
        log.info("kafka message key {}", record.key());
        log.info("kafka message value {}", record.value());
        log.info("Kafka message headers {}", record.headers());
        try {
            mongoImplementation.storeMessage(record.value());
        }
        catch (MongoException | MongoClientException | MongoInternalException mongoException){
            log.error("EXCEPTION: "+mongoException.getMessage());
        }
    }
}
