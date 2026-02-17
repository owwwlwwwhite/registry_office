package org.example;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.SoftAssertions;
import org.example.test_data.DataAPIFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.example.APISpecifications.BASE_SPECIFICATION;
import static org.example.EndpointsConstants.SEND_ADMIN_REQUEST_ENDPOINT;
import static org.example.EndpointsConstants.SEND_USER_REQUEST_ENDPOINT;

@Log4j2
public class DBBaseTest {
    public static Connection dbConnection;
    protected final SoftAssertions softly = new SoftAssertions();
    public static ResultSet resultSet;

    @BeforeAll
    static void setup() {
        Properties testProperties = new Properties();
        InputStream inputStream = App.class
                .getClassLoader()
                .getResourceAsStream("test_auth.properties");

        if (inputStream == null) {
            log.fatal("Файл test_auth.properties не найден в src/test/resources/");
            throw new RuntimeException("Файл test_auth.properties не найден в src/test/resources/");
        }

        try {
            testProperties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            log.fatal("io error");
        }

        testProperties.getProperty("url");

        try {
            Class.forName("org.postgresql.Driver");
            String url = testProperties.getProperty("db.url");
            String username = testProperties.getProperty("db.username");
            String password = testProperties.getProperty("db.password");

            dbConnection = DriverManager.getConnection(url, username, password);

            log.info("Established connection with register_office");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    static void exit() throws SQLException {
        dbConnection.close();
        log.info("Close connection with register_office");
    }

    @Step("Достать значение из поля {field} из запроса {json}")
    protected String extractValueBodyRequest(String field, String json) {
        String regex = "\"" + field + "\"\\s*:\\s*\"?([^\"\\n,}]*)\"?";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(json);

        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }

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
    protected int createAdmin() {
        Response response = given()
                .spec(BASE_SPECIFICATION)
                .body(DataAPIFactory.getSendAdminRequest())
                .post(SEND_ADMIN_REQUEST_ENDPOINT)
                .then()
                .statusCode(200)
                .extract().response();

        return response.body().jsonPath().getInt("data.staffid");
    }


    @Step("Проверить валидность записи в таблице merrigecertificates с citizenid={citizenid}")
    protected void verifyMerrigeCertificatesTableForUser(int citizenid, Statement statement) throws SQLException {
        resultSet = statement.executeQuery(String.format("""
                SELECT *
                FROM reg_office.merrigecertificates a
                where a.citizenid = %d;
                """, citizenid));
        resultSet.next();

        softly.assertThat(resultSet.getInt("merrigecertificateid"))
                .as("merrigecertificateid must be not 0")
                .isGreaterThan(0);

        softly.assertThat(resultSet.getTimestamp("dateofmerrige"))
                .as("dateofmerrige must be the same as in request dateOfMarriage")
                .isEqualTo(Timestamp.valueOf(
                        LocalDate.parse(
                                        Objects.requireNonNull(
                                                extractValueBodyRequest("dateOfMarriage", DataAPIFactory.getSendUserRequestWedding())))
                                .atStartOfDay()));

        softly.assertThat(resultSet.getString("surnameofspouse"))
                .as("surnameofspouse must be the same as in request anotherPersonLastName")
                .isEqualTo(extractValueBodyRequest("anotherPersonLastName", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(resultSet.getString("newsurnameofspouse"))
                .as("newsurnameofspouse must be the same as in request newLastName")
                .isEqualTo(extractValueBodyRequest("newLastName", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(resultSet.getString("nameofspouse"))
                .as("nameofspouse must be the same as in request anotherPersonFirstName")
                .isEqualTo(extractValueBodyRequest("anotherPersonFirstName", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(resultSet.getString("middlenameofspouse"))
                .as("middlenameofspouse must be the same as in request anotherPersonMiddleName")
                .isEqualTo(extractValueBodyRequest("anotherPersonMiddleName", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(resultSet.getString("passportnumberofspouse"))
                .as("passportnumberofspouse must be the same as in request anotherPersonPassport")
                .isEqualTo(extractValueBodyRequest("anotherPersonPassport", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(resultSet.getString("dateofbirthofspouse"))
                .as("dateofbirthofspouse must be the same as in request birth_of_anotoherPerson")
                .isEqualTo(extractValueBodyRequest("birth_of_anotoherPerson", DataAPIFactory.getSendUserRequestWedding()));
    }

    @Step("Проверить валидность записи в таблице applicants с applicantid={applicantid}")
    protected void verifyApplicantsTableForUser(int applicantid, Statement statement) throws SQLException {
        resultSet = statement.executeQuery(String.format("""
                SELECT *
                FROM reg_office.applicants a
                where a.applicantid = %d
                """, applicantid));
        resultSet.next();

        softly.assertThat(resultSet.getString("surname"))
                .as("surname must be the same as in request personalLastName")
                .isEqualTo(extractValueBodyRequest("personalLastName", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(resultSet.getString("name"))
                .as("name must be the same as in request personalFirstName")
                .isEqualTo(extractValueBodyRequest("personalFirstName", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(resultSet.getString("middlename"))
                .as("middlename must be the same as in request personalMiddleName")
                .isEqualTo(extractValueBodyRequest("personalMiddleName", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(resultSet.getString("passportnumber"))
                .as("passportnumber must be the same as in request personalNumberOfPassport")
                .isEqualTo(extractValueBodyRequest("personalNumberOfPassport", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(resultSet.getString("phonenumber"))
                .as("phonenumber must be the same as in request personalPhoneNumber")
                .isEqualTo(extractValueBodyRequest("personalPhoneNumber", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(resultSet.getString("registration_address"))
                .as("registration_address must be the same as in request personalAddress")
                .isEqualTo(extractValueBodyRequest("personalAddress", DataAPIFactory.getSendUserRequestWedding()));
    }

    @Step("Проверить валидность записи в таблице citizens с citizenid={citizenid}")
    protected void verifyCitizensTableForUser(int citizenid, Statement statement) throws SQLException {
        resultSet = statement.executeQuery(String.format("""
                SELECT *
                FROM reg_office.citizens a
                where a.citizenid = %d;
                """, citizenid));
        resultSet.next();

        softly.assertThat(resultSet.getString("surname"))
                .as("surname must be the same as in request citizenLastName")
                .isEqualTo(extractValueBodyRequest("citizenLastName", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(resultSet.getString("name"))
                .as("name must be the same as in request citizenFirstName")
                .isEqualTo(extractValueBodyRequest("citizenFirstName", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(resultSet.getString("middlename"))
                .as("middlename must be the same as in request citizenMiddleName")
                .isEqualTo(extractValueBodyRequest("citizenMiddleName", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(resultSet.getString("passportnumber"))
                .as("passportnumber must be the same as in request citizenNumberOfPassport")
                .isEqualTo(extractValueBodyRequest("citizenNumberOfPassport", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(resultSet.getTimestamp("dateofbirth"))
                .as("dateofbirth must be the same as in request citizenBirthDate")
                .isEqualTo(Timestamp.valueOf(
                        LocalDate.parse(
                                        Objects.requireNonNull(
                                                extractValueBodyRequest("citizenBirthDate", DataAPIFactory.getSendUserRequestWedding())))
                                .atStartOfDay()));

        softly.assertThat(resultSet.getString("gender"))
                .as("gender must be the same as in request citizenGender")
                .isEqualTo(extractValueBodyRequest("citizenGender", DataAPIFactory.getSendUserRequestWedding()));

        softly.assertThat(resultSet.getString("registration_address"))
                .as("registration_address must be the same as in request citizenAddress")
                .isEqualTo(extractValueBodyRequest("citizenAddress", DataAPIFactory.getSendUserRequestWedding()));
    }
}
