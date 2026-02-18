package org.example;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.SoftAssertions;
import org.example.DBTableRecords.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.sql.*;
import java.sql.DriverManager;
import java.util.Properties;

import static org.example.utils.propertyLoaderUtil.loadProperty;

@Log4j2
public class DBBaseTest {
    protected static Connection dbConnection;
    protected static Statement statement;
    protected final SoftAssertions softly = new SoftAssertions();
    protected static ResultSet resultSet;

    @BeforeAll
    static void setup() {
        Properties testProperties = loadProperty("test_auth.properties");
        String url = testProperties.getProperty("db.url");
        String username = testProperties.getProperty("db.username");
        String password = testProperties.getProperty("db.password");

        try {
            Class.forName("org.postgresql.Driver");
            dbConnection = DriverManager.getConnection(url, username, password);
            statement = dbConnection.createStatement();

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

    @Step("Извлечь запись из таблицы merrigecertificates с citizenid={citizenid}")
    protected MerrigeCertificatesTableRecord extractRecordFromMerrigeCertificatesTable(int citizenid) throws SQLException {
        resultSet = statement.executeQuery(String.format("""
                SELECT *
                FROM reg_office.merrigecertificates a
                where a.citizenid = %d;
                """, citizenid));
        resultSet.next();

        return new MerrigeCertificatesTableRecord(
                resultSet.getTimestamp("dateofmerrige").toLocalDateTime().toLocalDate(),
                resultSet.getString("surnameofspouse"),
                resultSet.getString("newsurnameofspouse"),
                resultSet.getString("nameofspouse"),
                resultSet.getString("middlenameofspouse"),
                resultSet.getString("passportnumberofspouse"),
                resultSet.getTimestamp("dateofbirthofspouse").toLocalDateTime().toLocalDate()
        );
    }

    @Step("Извлечь запись из таблицы applicants с applicantid={applicantid}")
    protected ApplicantsTableRecord extractRecordFromApplicantsTable(int applicantid) throws SQLException {
        resultSet = statement.executeQuery(String.format("""
                SELECT *
                FROM reg_office.applicants a
                where a.applicantid = %d
                """, applicantid));
        resultSet.next();

        return new ApplicantsTableRecord(
                resultSet.getString("surname"),
                resultSet.getString("name"),
                resultSet.getString("middlename"),
                resultSet.getString("passportnumber"),
                resultSet.getString("phonenumber"),
                resultSet.getString("registration_address")
        );

    }

    @Step("Извлечь запись из таблицы citizens с citizenid={citizenid}")
    protected CitizensTableRecord extractRecordFromCitizensTable(int citizenid) throws SQLException {
        resultSet = statement.executeQuery(String.format("""
                SELECT *
                FROM reg_office.citizens a
                where a.citizenid = %d;
                """, citizenid));
        resultSet.next();

        return new CitizensTableRecord(
                resultSet.getString("surname"),
                resultSet.getString("name"),
                resultSet.getString("middlename"),
                resultSet.getString("passportnumber"),
                resultSet.getTimestamp("dateofbirth").toLocalDateTime().toLocalDate(),
                resultSet.getString("gender"),
                resultSet.getString("registration_address")
        );
    }

    @Step("Извлечь запись из таблицы applications с applicationid={applicationid}")
    protected ApplicationsTableRecord extractRecordFromApplicationsTable(int applicationid) throws SQLException {
        resultSet = statement.executeQuery(String.format("""
                SELECT *
                FROM reg_office.applications a
                WHERE a.applicationid = %d;
                """, applicationid));
        resultSet.next();

        return new ApplicationsTableRecord(
                resultSet.getInt("applicationid"),
                resultSet.getInt("applicantid"),
                resultSet.getInt("citizenid"),
                resultSet.getTimestamp("dateofapplication").toLocalDateTime().toLocalDate(),
                resultSet.getString("kindofapplication")
        );
    }

    @Step("Извлечь запись из таблицы staff с staffid={staffid}")
    protected StaffTableRecord extractRecordFromStaffTable(int staffid) throws SQLException {
        statement = dbConnection.createStatement();

        resultSet = statement.executeQuery(String.format("""
                SELECT *
                FROM reg_office.staff a
                WHERE a.staffid = %d;
                """, staffid));
        resultSet.next();

        return new StaffTableRecord(
                resultSet.getString("surname"),
                resultSet.getString("name"),
                resultSet.getString("middlename"),
                resultSet.getTimestamp("dateofbirth").toLocalDateTime().toLocalDate(),
                resultSet.getString("passportnumber"),
                resultSet.getString("phonenumber")
        );
    }
}
