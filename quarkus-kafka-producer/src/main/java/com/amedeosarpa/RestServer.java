package com.amedeosarpa;

import com.amedeosarpa.dto.MeasurementRecord;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
@Path("/hello")
public class RestServer {

    @Inject
    KafkaProducer kafkaProducer;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String hello(MeasurementRecord measurementRecord) {
        log.info("Received request to send measurement {}", measurementRecord);
        kafkaProducer.produce(measurementRecord);
        return "It worked!";
    }
}
