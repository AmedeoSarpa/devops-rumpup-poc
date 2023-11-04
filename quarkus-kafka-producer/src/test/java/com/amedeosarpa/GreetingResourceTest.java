package com.amedeosarpa;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {

        String inputMessage = "Hello from kafka";

        given()
          .when()
                .body(inputMessage)
                .get("/hello")
          .then()
             .statusCode(200)
             .body(is(inputMessage));
    }

}