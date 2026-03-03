package org.example.db;

import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.sql.*;
import java.sql.DriverManager;
import java.util.Objects;
import java.util.Properties;

import static org.example.utils.PropertyLoaderUtil.loadProperty;

@Log4j2
public class DBBaseTest {
    protected static Connection dbConnection;
    protected static Statement statement;
    protected final SoftAssertions softly = new SoftAssertions();

    @BeforeAll
    static void setup() {
        Properties testProperties = loadProperty("test_auth.properties");
        String url = Objects.requireNonNull(testProperties).getProperty("db.url");
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


}
