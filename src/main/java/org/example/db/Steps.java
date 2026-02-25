package org.example.db;

import io.qameta.allure.Step;
import org.example.db.DBTableRecords.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Steps {
    @Step("Извлечь запись из таблицы merrigecertificates с citizenid={citizenid}")
    public static MerrigeCertificatesTableRecord extractRecordFromMerrigeCertificatesTable(int citizenId, Statement statement) throws SQLException {
        ResultSet resultSet = Query.selectMerrigeCertificateByCitizenId(statement, citizenId);
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
    public static ApplicantsTableRecord extractRecordFromApplicantsTable(int applicantId, Statement statement) throws SQLException {
        ResultSet resultSet = Query.selectApplicantByApplicantId(statement, applicantId);
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
    public static CitizensTableRecord extractRecordFromCitizensTable(int citizenId, Statement statement) throws SQLException {
        ResultSet resultSet = Query.selectCitizenByCitizenId(statement, citizenId);
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
    public static ApplicationsTableRecord extractRecordFromApplicationsTable(int applicationId, Statement statement) throws SQLException {
        ResultSet resultSet = Query.selectApplicationByApplicationId(statement, applicationId);
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
    public static StaffTableRecord extractRecordFromStaffTable(int staffId, Statement statement) throws SQLException {
        ResultSet resultSet = Query.selectStaffByStaffId(statement, staffId);
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
