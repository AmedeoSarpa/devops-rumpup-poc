package com.amedeosarpa;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;


@ApplicationScoped
@Slf4j
public class KafkaProducer {

    @Inject
    @Channel("data-output")
    Emitter<String> emitter;

    public void produce(String message){
        emitter.send(message);
    }
}
