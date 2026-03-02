package org.example;

import lombok.extern.log4j.Log4j2;
import org.example.ui.DriverManager;
import org.example.ui.pages.*;
import org.example.ui.valueObjects.Mode;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.util.Objects;
import java.util.Properties;

import static org.example.utils.PropertyLoaderUtil.loadProperty;

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
        driver = DriverManager.getInstance().getDriver();
        log.info("Setup driver");

        testProperties = loadProperty("test_auth.properties");

        driver.get(Objects.requireNonNull(testProperties).getProperty("url"));
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
