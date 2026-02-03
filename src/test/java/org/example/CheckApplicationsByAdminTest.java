package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.elements.Button;
import org.example.pages.*;
import org.example.valueObjects.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckApplicationsByAdminTest {
    private static Properties testProperties;
    private static WebDriver driver;
    private final Applicant applicant = new Applicant("Фа", "Им", "Отчес", "1234567", "123456", "абвг");
    private final Citizen citizen = new Citizen("Фа", "Им", "Отчес", "01062026", "муж", "123456", "абвг");
    private final ServiceData serviceDeathData = new ServiceData
            .Builder()
            .setDeathData("01.01.2000", "абвг")
            .build();
    private final ServiceData serviceMarriageData = new ServiceData
            .Builder()
            .setMarriageData("01012000",
                    "фф",
                    "фф",
                    "фф",
                    "ффыыв",
                    "01011995",
                    "123456")
            .build();
    private final ServiceData serviceBirthData = new ServiceData
            .Builder()
            .setBirthData("абвг", "аa", "аа", "аа", "аа")
            .build();
    private final Admin adminData = new Admin(
            "фф",
            "фф",
            "ффыыв",
            "1234567",
            "123456",
            "01012000");

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
    }

    @AfterAll
    static void exit() {
        DriverManager.getInstance().closeDriver();
    }

    private String createApplication(Mode mode, ServiceData serviceData) {

        new MainPage(driver).enterAsUser();
        assertEquals("Фамилия *", driver.findElement(new By.ByXPath("//label[text()='Фамилия']")).getText());

        new ApplicantDataPage(driver).fillFormAndSubmit(applicant);
        assertEquals("Регистрация брака", new Button(driver, "Регистрация брака").getWebElement().getText());

        new ChoiceOfService(driver).selectRegistration(mode);
        assertEquals("Фамилия *", driver.findElement(new By.ByXPath("//label[text()='Фамилия']")).getText());

        new CitizenDataPage(driver).fillFormAndSubmit(citizen);
        switch(mode) {
            case MARRIAGE:
                assertEquals("Дата регистрации *", driver.findElement(new By.ByXPath("//label[text()='Дата регистрации']")).getText());
                break;
            case BIRTH:
                assertEquals("Место рождения *", driver.findElement(new By.ByXPath("//label[text()='Место рождения']")).getText());
                break;
            case DEATH:
                assertEquals("Дата смерти *", driver.findElement(new By.ByXPath("//label[text()='Дата смерти']")).getText());
                break;
        }

        new ServiceDataPage(driver, mode).fillFormAndSubmit(serviceData);
        assertEquals("Создать новую заявку", driver.findElement(new By.ByXPath("//button[text()='Создать новую заявку']")).getText());

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement spanWithApplicationNumber = driver.findElement(new By.ByXPath("//span[contains(text(), 'отправлена на рассмотрение')]"));
        return spanWithApplicationNumber.getText().split(" ")[3];
    }

    private boolean applicationNumberIsExist(String applicationNumber) {
        driver.get(testProperties.getProperty("url"));
        new MainPage(driver).enterAsAdmin();
        assertEquals("Фамилия *", driver.findElement(new By.ByXPath("//label[text()='Фамилия']")).getText());

        new AdminRegistrationPage(driver).fillFormAndSubmit(adminData);
        assertEquals("Обновить", new Button(driver, "Обновить").getWebElement().getText());

        return new ApplicationsAdministrationPage(driver).isApplicationNumberEqualsExpected(applicationNumber);
    }

    @Test
    void checkMarriageApplicationCreation() {
        String applicationNumber = createApplication(Mode.MARRIAGE, serviceMarriageData);

        assertTrue(applicationNumberIsExist(applicationNumber));
    }

    @Test
    void checkDeathApplicationCreation() {
        String applicationNumber = createApplication(Mode.DEATH, serviceDeathData);

        assertTrue(applicationNumberIsExist(applicationNumber));
    }

    @Test
    void checkBirthApplicationCreation() {
        String applicationNumber = createApplication(Mode.BIRTH, serviceBirthData);

        assertTrue(applicationNumberIsExist(applicationNumber));
    }
}
