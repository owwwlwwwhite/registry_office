package org.example;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import lombok.extern.log4j.Log4j2;
import org.example.test_data.DataAPIFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.Objects;

@Log4j2
@DisplayName("Раздел DB")
public class DBTest extends DBBaseTest {
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка целостности записи в БД заявки на регистрацию брака")
    void checkUserRegisterApplication() throws SQLException {
        int applicationIdAPI = createApplication();

        Statement statement = dbConnection.createStatement();

        resultSet = statement.executeQuery(String.format("""
                SELECT *
                FROM reg_office.applications a
                WHERE a.applicationid = %d;
                """, applicationIdAPI));
        resultSet.next();

        softly.assertThat(resultSet.getInt("applicationid"))
                .as("applicationId is not 0")
                .isGreaterThan(0);

        int applicantid = resultSet.getInt("applicantid");
        int citizenid = resultSet.getInt("citizenid");

        softly.assertThat(applicantid)
                .as("applicantid is not 0")
                .isGreaterThan(0);

        softly.assertThat(citizenid)
                .as("citizenid is not 0")
                .isGreaterThan(0);

        softly.assertThat(resultSet.getTimestamp("dateofapplication"))
                .as("dateofapplication is not null")
                .isNotNull();

        softly.assertThat(resultSet.getString("kindofapplication"))
                .as("applicantid is not 0")
                .isEqualTo("Получение свидетельства о браке");

        verifyApplicantsTableForUser(applicantid, statement);
        verifyCitizensTableForUser(citizenid, statement);
        verifyMerrigeCertificatesTableForUser(citizenid, statement);

        softly.assertAll();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка целостности записи в БД заявки на регистрацию брака")
    void checkAdminRegistration() throws SQLException {
        int staffid = createAdmin();

        Statement statement = dbConnection.createStatement();

        resultSet = statement.executeQuery(String.format("""
                SELECT *
                FROM reg_office.staff a
                WHERE a.staffid = %d;
                """, staffid));
        resultSet.next();

        softly.assertThat(resultSet.getString("surname"))
                .as("surname must be the same as in request personalLastName")
                .isEqualTo(extractValueBodyRequest("personalLastName", DataAPIFactory.getSendAdminRequest()));

        softly.assertThat(resultSet.getString("name"))
                .as("name must be the same as in request personalFirstName")
                .isEqualTo(extractValueBodyRequest("personalFirstName", DataAPIFactory.getSendAdminRequest()));

        softly.assertThat(resultSet.getString("middlename"))
                .as("middlename must be the same as in request personalMiddleName")
                .isEqualTo(extractValueBodyRequest("personalMiddleName", DataAPIFactory.getSendAdminRequest()));

        softly.assertThat(resultSet.getTimestamp("dateofbirth"))
                .as("dateofbirth must be the same as in request dateofbirth")
                .isEqualTo(Timestamp.valueOf(
                        LocalDate.parse(
                                        Objects.requireNonNull(
                                                extractValueBodyRequest("dateofbirth", DataAPIFactory.getSendAdminRequest())))
                                .atStartOfDay()));

        softly.assertThat(resultSet.getString("passportnumber"))
                .as("passportnumber must be the same as in request personalNumberOfPassport")
                .isEqualTo(extractValueBodyRequest("personalNumberOfPassport", DataAPIFactory.getSendAdminRequest()));

        softly.assertThat(resultSet.getString("phonenumber"))
                .as("phonenumber must be the same as in request personalPhoneNumber")
                .isEqualTo(extractValueBodyRequest("personalPhoneNumber", DataAPIFactory.getSendAdminRequest()));

        softly.assertAll();
    }
}