package org.example.db;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.test_data.DataAPIFactory;

import static io.restassured.RestAssured.given;
import static org.example.api.APISpecifications.BASE_SPECIFICATION;
import static org.example.api.EndpointsConstants.SEND_ADMIN_REQUEST_ENDPOINT;
import static org.example.api.EndpointsConstants.SEND_USER_REQUEST_ENDPOINT;

public class APISteps {
    @Step("Создать заявку о регистрации брака")
    static int createApplication() {
        Response response = given()
                .spec(BASE_SPECIFICATION)
                .body(DataAPIFactory.getSendUserRequestWedding())
                .post(SEND_USER_REQUEST_ENDPOINT)
                .then()
                .statusCode(200)
                .extract().response();

        return response.body().jsonPath().getInt("data.applicationid");
    }

    @Step("Создать админа")
    static int createAdmin() {
        Response response = given()
                .spec(BASE_SPECIFICATION)
                .body(DataAPIFactory.getSendAdminRequest())
                .post(SEND_ADMIN_REQUEST_ENDPOINT)
                .then()
                .statusCode(200)
                .extract().response();

        return response.body().jsonPath().getInt("data.staffid");
    }
}
