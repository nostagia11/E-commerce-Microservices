package com.eprit.productservice;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsRestAssuredConfigurationCustomizer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;
import io.restassured.RestAssured;

import java.util.regex.Matcher;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo: 7.0.5");
    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

    }

    static {
        mongoDBContainer.start();
    }

    @Test
    void ShouldCreateProduct() {
        String RequestBody = """
                "name" : "Spring Batch",
                "description" : "Oreilly book about spring batch",
                "price" : "200"
                
                """;
        RestAssured.given()
                .contentType("Application/json")
                .body(RequestBody)
                .when()
                .post("/api/product")
                .then()
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("name", Matchers.equalTo("Spring Batch"))
                .body("description", Matchers.equalTo("Oreilly book about spring batch"))
                .body("price", Matchers.equalTo(200))
                 ;
    }

}
