package com.amedeosarpa.dto;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class MeasurementRecordDeserializer extends ObjectMapperDeserializer<MeasurementRecord> {

    public MeasurementRecordDeserializer(){
        super(MeasurementRecord.class);
    }
}
