package org.example;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.example.db.DBTableRecords.*;
import org.example.test_data.DataAPIFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.example.api.APISpecifications.BASE_SPECIFICATION;
import static org.example.api.EndpointsConstants.SEND_ADMIN_REQUEST_ENDPOINT;
import static org.example.api.EndpointsConstants.SEND_USER_REQUEST_ENDPOINT;
import static org.example.db.Steps.*;
import static org.example.utils.jsonUtil.extractValueBodyRequest;

@Log4j2
@DisplayName("Раздел DB")
public class DBTest extends DBBaseTest {
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

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка целостности записи в БД заявки на регистрацию брака")
    void checkUserRegisterApplication() throws SQLException {
        int applicationIdAPI = createApplication();

        ApplicationsTableRecord applicationsTableRecord = extractRecordFromApplicationsTable(applicationIdAPI, statement);
        CitizensTableRecord citizensTableRecord = extractRecordFromCitizensTable(applicationsTableRecord.citizenid(), statement);
        ApplicantsTableRecord applicantsTableRecord = extractRecordFromApplicantsTable(applicationsTableRecord.applicantid(), statement);
        MerrigeCertificatesTableRecord merrigeCertificatesTableRecord = extractRecordFromMerrigeCertificatesTable(applicationsTableRecord.citizenid(), statement);

        // APPLICATIONS TABLE

        softly.assertThat(applicationsTableRecord.applicationid())
                .as("applicationId is not 0")
                .isGreaterThan(0);

        softly.assertThat(applicationsTableRecord.applicantid())
                .as("applicantid is not 0")
                .isGreaterThan(0);

        softly.assertThat(applicationsTableRecord.citizenid())
                .as("citizenid is not 0")
                .isGreaterThan(0);

        softly.assertThat(applicationsTableRecord.dateofapplication())
                .as("dateofapplication is not null")
                .isEqualTo(LocalDate.now().atStartOfDay());

        softly.assertThat(applicationsTableRecord.kindofapplication())
                .as("kindofapplication is 'Получение свидетельства о браке'")
                .isEqualTo("Получение свидетельства о браке");

        // CITIZENS TABLE

        softly.assertThat(citizensTableRecord.surname())
                .as("surname must be the same as in request citizenLastName")
                .isEqualTo(extractValueBodyRequest("citizenLastName", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(citizensTableRecord.name())
                .as("name must be the same as in request citizenFirstName")
                .isEqualTo(extractValueBodyRequest("citizenFirstName", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(citizensTableRecord.middlename())
                .as("middlename must be the same as in request citizenMiddleName")
                .isEqualTo(extractValueBodyRequest("citizenMiddleName", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(citizensTableRecord.dateofbirth())
                .as("passportnumber must be the same as in request citizenNumberOfPassport")
                .isEqualTo(extractValueBodyRequest("citizenNumberOfPassport", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(citizensTableRecord.dateofbirth())
                .as("dateofbirth must be the same as in request citizenBirthDate")
                .isEqualTo(LocalDate.parse(
                                Objects.requireNonNull(
                                        extractValueBodyRequest("citizenBirthDate", DataAPIFactory.getSendUserRequestWedding())))
                        .atStartOfDay());

        softly.assertThat(citizensTableRecord.gender())
                .as("gender must be the same as in request citizenGender")
                .isEqualTo(extractValueBodyRequest("citizenGender", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(citizensTableRecord.registration_address())
                .as("registration_address must be the same as in request citizenAddress")
                .isEqualTo(extractValueBodyRequest("citizenAddress", DataAPIFactory.getSendUserRequestWedding()));

        // APPLICANTS TABLE

        softly.assertThat(applicantsTableRecord.surname())
                .as("surname must be the same as in request personalLastName")
                .isEqualTo(extractValueBodyRequest("personalLastName", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(applicantsTableRecord.name())
                .as("name must be the same as in request personalFirstName")
                .isEqualTo(extractValueBodyRequest("personalFirstName", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(applicantsTableRecord.middlename())
                .as("middlename must be the same as in request personalMiddleName")
                .isEqualTo(extractValueBodyRequest("personalMiddleName", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(applicantsTableRecord.passportnumber())
                .as("passportnumber must be the same as in request personalNumberOfPassport")
                .isEqualTo(extractValueBodyRequest("personalNumberOfPassport", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(applicantsTableRecord.phonenumber())
                .as("phonenumber must be the same as in request personalPhoneNumber")
                .isEqualTo(extractValueBodyRequest("personalPhoneNumber", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(applicantsTableRecord.registration_address())
                .as("registration_address must be the same as in request personalAddress")
                .isEqualTo(extractValueBodyRequest("personalAddress", DataAPIFactory.getSendUserRequestWedding()));

        // MERRIGE CERTIFICATES TABLE

        softly.assertThat(merrigeCertificatesTableRecord.dateofmerrige())
                .as("dateofmerrige must be the same as in request dateOfMarriage")
                .isEqualTo(LocalDate.parse(
                                Objects.requireNonNull(
                                        extractValueBodyRequest("dateOfMarriage", DataAPIFactory.getSendUserRequestWedding())))
                        .atStartOfDay());

        softly.assertThat(merrigeCertificatesTableRecord.surnameofspouse())
                .as("surnameofspouse must be the same as in request anotherPersonLastName")
                .isEqualTo(extractValueBodyRequest("anotherPersonLastName", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(merrigeCertificatesTableRecord.newsurnameofspouse())
                .as("newsurnameofspouse must be the same as in request newLastName")
                .isEqualTo(extractValueBodyRequest("newLastName", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(merrigeCertificatesTableRecord.nameofspouse())
                .as("nameofspouse must be the same as in request anotherPersonFirstName")
                .isEqualTo(extractValueBodyRequest("anotherPersonFirstName", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(merrigeCertificatesTableRecord.middlenameofspouse())
                .as("middlenameofspouse must be the same as in request anotherPersonMiddleName")
                .isEqualTo(extractValueBodyRequest("anotherPersonMiddleName", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(merrigeCertificatesTableRecord.passportnumberofspouse())
                .as("passportnumberofspouse must be the same as in request anotherPersonPassport")
                .isEqualTo(extractValueBodyRequest("anotherPersonPassport", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(merrigeCertificatesTableRecord.dateofbirthofspouse())
                .as("dateofbirthofspouse must be the same as in request birth_of_anotoherPerson")
                .isEqualTo(LocalDate.parse(
                                Objects.requireNonNull(
                                        extractValueBodyRequest("birth_of_anotoherPerson", DataAPIFactory.getSendUserRequestWedding())))
                        .atStartOfDay());

        softly.assertAll();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка целостности записи в БД заявки на регистрацию брака")
    void checkAdminRegistration() throws SQLException {
        int staffid = createAdmin();

        StaffTableRecord staffTableRecord = extractRecordFromStaffTable(staffid, statement);

        softly.assertThat(staffTableRecord.surname())
                .as("surname must be the same as in request personalLastName")
                .isEqualTo(extractValueBodyRequest("personalLastName", DataAPIFactory.getSendAdminRequest()));

        softly.assertThat(staffTableRecord.name())
                .as("name must be the same as in request personalFirstName")
                .isEqualTo(extractValueBodyRequest("personalFirstName", DataAPIFactory.getSendAdminRequest()));

        softly.assertThat(staffTableRecord.middlename())
                .as("middlename must be the same as in request personalMiddleName")
                .isEqualTo(extractValueBodyRequest("personalMiddleName", DataAPIFactory.getSendAdminRequest()));

        softly.assertThat(staffTableRecord.dateofbirth())
                .as("dateofbirth must be the same as in request dateofbirth")
                .isEqualTo(
                        LocalDate.parse(
                                Objects.requireNonNull(
                                        extractValueBodyRequest("dateofbirth", DataAPIFactory.getSendAdminRequest())))
                                .atStartOfDay());

        softly.assertThat(staffTableRecord.passportnumber())
                .as("passportnumber must be the same as in request personalNumberOfPassport")
                .isEqualTo(extractValueBodyRequest("personalNumberOfPassport", DataAPIFactory.getSendAdminRequest()));

        softly.assertThat(staffTableRecord.phonenumber())
                .as("phonenumber must be the same as in request personalPhoneNumber")
                .isEqualTo(extractValueBodyRequest("personalPhoneNumber", DataAPIFactory.getSendAdminRequest()));

        softly.assertAll();
    }
}