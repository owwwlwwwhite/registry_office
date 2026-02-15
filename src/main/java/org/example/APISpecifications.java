package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.example.utils.RestAssuredCustomLogger;

import java.net.URI;

public class APISpecifications {
    public static final RequestSpecification BASE_SPECIFICATION = new RequestSpecBuilder()
            .setBaseUri(URI.create("https://regoffice.senla.eu/"))
            .setAuth(RestAssured.basic("user", "senlatest"))
            .setContentType("application/json")
            .addHeader("Accept", "*/*")
            .addHeader("Accept-Encoding", "gzip, deflate, br")
            .addHeader("Connection", "keep-alive")
            .addFilter(new RestAssuredCustomLogger())
            .build();
}
