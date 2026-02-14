package org.example.utils;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class RestAssuredCustomLogger implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        // запрос
        log.info("\n========== API REQUEST ==========\n" +
                        "Method: {}\n" +
                        "URI: {}\n" +
                        "Headers: {}\n" +
                        "Body: \n{}",
                requestSpec.getMethod(),
                requestSpec.getURI(),
                requestSpec.getHeaders(),
                requestSpec.getBody() == null ? "None" : requestSpec.getBody().toString());

        Response response = ctx.next(requestSpec, responseSpec);

        // ответ
        log.info("\n========== API RESPONSE ==========\n" +
                        "Status Code: {}\n" +
                        "Headers: {}\n" +
                        "Body: \n{}",
                response.getStatusCode(),
                response.getHeaders(),
                response.asPrettyString());

        return response;
    }
}