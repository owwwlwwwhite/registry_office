package org.example;

import POJO.*;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.example.APIConstants.*;
import static org.example.APISpecifications.BASE_SPECIFICATION;

import org.example.test_data.DataAPIFactory;

@Log4j2
@DisplayName("Раздел API")
public class APITest {
    private final SoftAssertions softly = new SoftAssertions();

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link(url = "https://app.qase.io/project/ZDYS?case=255&previewMode=side&suite=79&tab=")
    @DisplayName("Проверка создания пользователя с валидными данными")
    void sendUserRequest() {
        Response response = given()
                .spec(BASE_SPECIFICATION)
                .body(DataAPIFactory.getSendUserRequestWedding())
                .post(SEND_USER_REQUEST_ENDPOINT)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/UserResp.json"))
                .extract().response();
        UserResp userResp = response.as(UserResp.class);

        softly.assertThat(userResp.getApplicationId())
                .as("Application ID")
                .isGreaterThan(0);

        softly.assertThat(userResp.getApplicantId())
                .as("Applicant ID")
                .isGreaterThan(0);

        softly.assertThat(userResp.getCitizenId())
                .as("Citizen ID")
                .isGreaterThan(0);
        softly.assertAll();
    }


    @Step("Создать заявку о регистрации брака")
    int createApplication() {
        Response response = given()
                .spec(BASE_SPECIFICATION)
                .body(DataAPIFactory.getSendUserRequestWedding())
                .post(SEND_USER_REQUEST_ENDPOINT);

        return response.body().jsonPath().getInt("data.applicationid");
    }


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link(url = "https://app.qase.io/project/ZDYS?case=285&previewMode=side&suite=86&tab=")
    @DisplayName("Запрос данных по валидному ID, проверка возвращения корректных данных")
    void getApplStatusByApplicationId() {
        int applicationNumber = createApplication();

        ApplicationResp applicationResp;

        Response response = given()
                .spec(BASE_SPECIFICATION)
                .param(String.valueOf(applicationNumber))
                .get(GET_APPLICATION_BY_STATUS_ENDPOINT)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/applicationResp.json"))
                .extract().response();

        applicationResp = response.as(ApplicationResp.class);

        softly.assertThat(applicationResp.getApplicantId())
                .as("Applicant Id больше нуля")
                .isGreaterThan(0);

        softly.assertThat(applicationResp.getDateOfApplication())
                .as("Date Of Application больше нуля")
                .isGreaterThan(0);

        softly.assertThat(applicationResp.getKindOfApplication())
                .as("Kind Of Application больше нуля")
                .isGreaterThan(0);

        softly.assertThat(applicationResp.getStatusOfApplication())
                .as("Status Of Application больше нуля")
                .isGreaterThan(0);
        softly.assertAll();
    }


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link(url = "https://app.qase.io/project/ZDYS?previewMode=modal&suite=88&tab=&case=287")
    @DisplayName("Получение заполненного списка заявок")
    void getApplications() {
        Response response = given()
                .spec(BASE_SPECIFICATION)
                .get(GET_APPLICATIONS_ENDPOINT)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/applicationsResp.json"))
                .extract().response();

        ApplicationsResp applicationsResp = response.as(ApplicationsResp.class);

        softly.assertThat(applicationsResp.getApplicantId())
                .as("Applicant Id больше нуля")
                .isGreaterThan(0);

        softly.assertThat(applicationsResp.getApplicationId())
                .as("Application Id больше нуля")
                .isGreaterThan(0);

        softly.assertThat(applicationsResp.getCitizenId())
                .as("Citizen Id больше нуля")
                .isGreaterThan(0);

        softly.assertThat(applicationsResp.getDateOfApplication())
                .as("Date Of Application строка")
                .asString();

        softly.assertThat(applicationsResp.getKindOfApplication())
                .as("Kind Of Application строка")
                .asString();

        softly.assertThat(applicationsResp.getStatusOfApplication())
                .as("Status Of Application больше нуля")
                .isGreaterThan(0);

        softly.assertThat(applicationsResp.getStaffId())
                .as("Staff Id больше нуля")
                .isGreaterThan(0);
        softly.assertAll();
    }


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка запроса на создание админа с валидными данными")
    void sendAdminRequest() {
        Response response = given()
                .spec(BASE_SPECIFICATION)
                .body(DataAPIFactory.getSendAdminRequest())
                .post(SEND_ADMIN_REQUEST_ENDPOINT)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/createAdminResp.json"))
                .extract().response();

        int staffId = response.as(Integer.class);

        softly.assertThat(staffId)
                .as("Staff Id больше нуля")
                .isGreaterThan(0);
        softly.assertAll();
    }


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка изменения состояния существующей заявки")
    void requestProcess() {
        Response response = given()
                .spec(BASE_SPECIFICATION)
                .body(DataAPIFactory.getChangeApplStatus())
                .post(REQUEST_PROCESS_ENDPOINT)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/changeApplStatusResp.json"))
                .extract().response();

            ChangeApplStatusResp changeApplStatusResp = response.as(ChangeApplStatusResp.class);

            softly.assertThat(changeApplStatusResp.getApplicantid())
                    .as("Applicant Id больше нуля")
                    .isGreaterThan(0);

            softly.assertThat(changeApplStatusResp.getApplicationid())
                    .as("Application Id больше нуля")
                    .isGreaterThan(0);

            softly.assertThat(changeApplStatusResp.getCitizenid())
                    .as("Citizen Id больше нуля")
                    .isGreaterThan(0);

            softly.assertThat(changeApplStatusResp.getDateofapplication())
                    .as("Date of application строка")
                    .asString();

            softly.assertThat(changeApplStatusResp.getKindofapplication())
                    .as("Kind of application строка")
                    .asString();

            softly.assertThat(changeApplStatusResp.getStatusofapplication())
                    .as("Status of application больше нуля")
                    .isGreaterThan(0);

            softly.assertThat(changeApplStatusResp.getStaffid())
                    .as("Staff Id больше нуля")
                    .isGreaterThan(0);
        softly.assertAll();
    }
}
