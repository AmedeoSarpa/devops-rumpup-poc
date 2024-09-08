package com.amedeosarpa.mongo;

import com.amedeosarpa.dto.MeasurementRecord;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;

import java.time.Clock;

@ApplicationScoped
public class MongoImplementation {

    private MongoClient mongoClient;

    private Clock clock;

    private static final String DATABASE_NAME = "devops-rumpup-database";
    private static final String COLLECTION_NAME = "messages";

    @Inject
    public MongoImplementation(MongoClient mongoClient){
        this.mongoClient = mongoClient;
        this.clock = Clock.systemUTC();
    }

    public void storeMessage(MeasurementRecord message) throws MongoException {
        Document document = new Document()
                .append("value", message.value())
                .append("location", message.location())
                .append("operatorId", message.operatorId())
                .append("time", this.clock.instant());
        InsertOneResult result = this.getMessagesCollection().insertOne(document);
        if (!result.wasAcknowledged()){
            throw new MongoException("Store Message operation failed");
        }
    }

    private MongoCollection getMessagesCollection(){
        return this.mongoClient.getDatabase(DATABASE_NAME).getCollection(COLLECTION_NAME);
    }

}
