package org.example.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.example.utils.RestAssuredCustomLogger;

import java.net.URI;
import java.util.Objects;

import static org.example.utils.propertyLoaderUtil.loadProperty;

public class APISpecifications {
    public static final RequestSpecification BASE_SPECIFICATION = new RequestSpecBuilder()
            .setBaseUri(URI.create("https://regoffice.senla.eu/"))
            .setAuth(RestAssured.basic(
                    Objects.requireNonNull(loadProperty("test_auth.properties")).getProperty("api.username"),
                    Objects.requireNonNull(loadProperty("test_auth.properties")).getProperty("api.password")))
            .setContentType("application/json")
            .addHeader("Accept", "*/*")
            .addHeader("Accept-Encoding", "gzip, deflate, br")
            .addHeader("Connection", "keep-alive")
            .addFilter(new RestAssuredCustomLogger())
            .addFilter(new AllureRestAssured())
            .build();
}
