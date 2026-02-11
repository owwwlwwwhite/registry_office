package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.example.pages.*;
import org.example.valueObjects.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class BaseTest {
    protected WebDriver driver;
    protected Properties testProperties;

    protected MainPage mainPage;
    protected AdminRegistrationPage adminRegistrationPage;
    protected ApplicantDataPage applicantDataPage;
    protected ApplicationsAdministrationPage applicationsAdministrationPage;
    protected ApplicationStatusPage applicationStatusPage;
    protected ChoiceOfServicePage choiceOfServicePage;
    protected CitizenDataPage citizenDataPage;
    protected ServiceDataPage marriageServiceDataPage;
    protected ServiceDataPage deathServiceDataPage;
    protected ServiceDataPage birthServiceDataPage;

    @BeforeEach
    void setup() {

        WebDriverManager.chromedriver().setup();
        driver = DriverManager.getInstance().getDriver();
        driver.manage().window().maximize();
        log.info("Setup driver");

        testProperties = new Properties();
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
            log.fatal("io error - marriage_application.before");
        }

        driver.get(testProperties.getProperty("url"));
        log.info("Open Main page");

        mainPage = new MainPage(driver);
        adminRegistrationPage = new AdminRegistrationPage(driver);
        applicantDataPage = new ApplicantDataPage(driver);
        applicationsAdministrationPage = new ApplicationsAdministrationPage(driver);
        applicationStatusPage = new ApplicationStatusPage(driver);
        choiceOfServicePage = new ChoiceOfServicePage(driver);
        citizenDataPage = new CitizenDataPage(driver);
        marriageServiceDataPage = new ServiceDataPage(driver, Mode.MARRIAGE);
        deathServiceDataPage = new ServiceDataPage(driver, Mode.DEATH);
        birthServiceDataPage = new ServiceDataPage(driver, Mode.BIRTH);

        log.info("Initialized all pages");
    }

    @AfterAll
    static void exit() {
        DriverManager.getInstance().closeDriver();
    }
}
