package com.amedeosarpa;

import com.amedeosarpa.dto.MeasurementRecord;
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
    Emitter<MeasurementRecord> emitter;

    public void produce(MeasurementRecord measurementRecord){
        emitter.send(measurementRecord);
    }
}
