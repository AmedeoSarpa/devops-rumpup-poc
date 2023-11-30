package com.amedeosarpa;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
@Path("/hello")
public class RestServer {

    @Inject
    KafkaProducer kafkaProducer;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(String message) {
        log.info("Received request to send message {}", message);
        kafkaProducer.produce(message);
        return "It worked!";
    }
}
