package org.example;

import io.qameta.allure.Link;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.example.test_data.DataAPIFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.example.APISpecifications.BASE_SPECIFICATION;
import static org.example.APITest.createApplication;
import static org.example.EndpointsConstants.SEND_USER_REQUEST_ENDPOINT;

@Log4j2
@DisplayName("Раздел DB")
public class DBTest {
    public static Connection dbConnection;

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
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    static void exit() throws SQLException {
        dbConnection.close();
    }

    @Step
    private Response registerAs(String endPoint, String body) {
        return given()
                .spec(BASE_SPECIFICATION)
                .body(body)
                .post(endPoint);
    }

    @Test
//    @Severity(SeverityLevel.CRITICAL)
    @Link(url = "")
    @DisplayName("")
    void checkUserRegisterApplication() throws SQLException {
        Response response = registerAs(SEND_USER_REQUEST_ENDPOINT, DataAPIFactory.getSendUserRequestWedding());

        Statement statement = dbConnection.createStatement();


    }
}