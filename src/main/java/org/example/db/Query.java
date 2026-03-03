package org.example.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
    private static ResultSet rs;

    public static ResultSet selectMerrigeCertificateByCitizenId(Statement statement, int citizenId) throws SQLException {
        rs = statement.executeQuery(String.format(
                """
                        SELECT *
                        FROM reg_office.merrigecertificates a
                        where a.citizenid = %d;
                        """, citizenId
        ));

        return rs;
    }

    public static ResultSet selectApplicantByApplicantId(Statement statement, int applicantId) throws SQLException {
        rs = statement.executeQuery(String.format("""
                SELECT *
                FROM reg_office.applicants a
                where a.applicantid = %d
                """, applicantId));

        return rs;
    }

    public static ResultSet selectCitizenByCitizenId(Statement statement, int citizenId) throws SQLException {
        rs = statement.executeQuery(String.format("""
                SELECT *
                FROM reg_office.citizens a
                where a.citizenid = %d;
                """, citizenId));

        return rs;
    }

    public static ResultSet selectApplicationByApplicationId(Statement statement, int applicationId) throws SQLException {
        rs = statement.executeQuery(String.format("""
                SELECT *
                FROM reg_office.applications a
                WHERE a.applicationid = %d;
                """, applicationId));

        return rs;
    }

    public static ResultSet selectStaffByStaffId(Statement statement, int stuffId) throws SQLException {
        rs = statement.executeQuery(String.format("""
                SELECT *
                FROM reg_office.staff a
                WHERE a.staffid = %d;
                """, stuffId));

        return rs;
    }

    public static ResultSet selectTableByFiledValue(Statement statement, String table, String field, String value) throws SQLException {
        rs = statement.executeQuery(String.format("""
                SELECT *
                FROM reg_office.%s a
                WHERE a.%s = %s;
                """, table, field, value));

        return rs;
    }

}
