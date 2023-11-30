package com.amedeosarpa.mongo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

public class MongoException extends Exception{

    public MongoException(String message) {
        super(message);
    }

}
