package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.*;
import org.example.valueObjects.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected Properties testProperties;
    protected ApplicationSteps applicationSteps;
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

        testProperties = new Properties();
        InputStream inputStream = App.class
                .getClassLoader()
                .getResourceAsStream("test_auth.properties");

        if (inputStream == null) {
            throw new RuntimeException("Файл test_auth.properties не найден в src/test/resources/");
        }

        try {
            testProperties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            System.out.println("io error - marriage_application.before");
        }

        driver.get(testProperties.getProperty("url"));

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
//        applicationSteps = new ApplicationSteps(driver);
    }

    @AfterAll
    static void exit() {
        DriverManager.getInstance().closeDriver();
    }

}
