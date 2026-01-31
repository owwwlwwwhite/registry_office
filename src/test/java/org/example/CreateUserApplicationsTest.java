package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.elements.Button;
import org.example.pages.*;
import org.example.valueObjects.Applicant;
import org.example.valueObjects.Citizen;
import org.example.valueObjects.ServiceData;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateUserApplicationsTest {

    private static WebDriver driver;
    private MainPage mainPage;
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


    @BeforeEach
    void setup() {
        WebDriverManager.chromedriver().setup();

        driver = DriverManager.getInstance().getDriver();
        driver.manage().window().maximize();

        Properties testProperties = new Properties();
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


    @Test
    void RegisterDeathApplication() {

        Mode mode = Mode.DEATH;

        new MainPage(driver).enterAsUser();
        assertEquals("Фамилия *", driver.findElement(new By.ByXPath("//label[text()='Фамилия']")).getText());

        new ApplicantDataPage(driver).fillFormAndSubmit(applicant);
        assertEquals("Регистрация брака", new Button(driver, "Регистрация брака").getWebElement().getText());

        new ChoiceOfService(driver).selectRegistration(mode);
        assertEquals("Фамилия *", driver.findElement(new By.ByXPath("//label[text()='Фамилия']")).getText());

        new CitizenDataPage(driver).fillFormAndSubmit(citizen);
        assertEquals("Дата смерти *", driver.findElement(new By.ByXPath("//label[text()='Дата смерти']")).getText());

        new ServiceDataPage(driver, mode).fillFormAndSubmit(serviceDeathData);
        assertEquals("Создать новую заявку", driver.findElement(new By.ByXPath("//button[text()='Создать новую заявку']")).getText());
    }

    @Test
    void registerBirthApplication() {
        Mode mode = Mode.BIRTH;

        new MainPage(driver).enterAsUser();
        assertEquals("Фамилия *", driver.findElement(new By.ByXPath("//label[text()='Фамилия']")).getText());

        new ApplicantDataPage(driver).fillFormAndSubmit(applicant);
        assertEquals("Регистрация брака", new Button(driver, "Регистрация брака").getWebElement().getText());

        new ChoiceOfService(driver).selectRegistration(mode);
        assertEquals("Фамилия *", driver.findElement(new By.ByXPath("//label[text()='Фамилия']")).getText());

        new CitizenDataPage(driver).fillFormAndSubmit(citizen);
        assertEquals("Место рождения *", driver.findElement(new By.ByXPath("//label[text()='Место рождения']")).getText());

        new ServiceDataPage(driver, mode).fillFormAndSubmit(serviceBirthData);
        assertEquals("Создать новую заявку", driver.findElement(new By.ByXPath("//button[text()='Создать новую заявку']")).getText());
    }

    @Test
    void registerMarriageApplication() {
        Mode mode = Mode.MARRIAGE;

        new MainPage(driver).enterAsUser();
        assertEquals("Фамилия *", driver.findElement(new By.ByXPath("//label[text()='Фамилия']")).getText());

        new ApplicantDataPage(driver).fillFormAndSubmit(applicant);
        assertEquals("Регистрация брака", new Button(driver, "Регистрация брака").getWebElement().getText());

        new ChoiceOfService(driver).selectRegistration(mode);
        assertEquals("Фамилия *", driver.findElement(new By.ByXPath("//label[text()='Фамилия']")).getText());

        new CitizenDataPage(driver).fillFormAndSubmit(citizen);
        assertEquals("Дата регистрации *", driver.findElement(new By.ByXPath("//label[text()='Дата регистрации']")).getText());

        new ServiceDataPage(driver, mode).fillFormAndSubmit(serviceMarriageData);
        assertEquals("Создать новую заявку", driver.findElement(new By.ByXPath("//button[text()='Создать новую заявку']")).getText());
    }


    @AfterAll
    static void exit() {

        DriverManager.getInstance().closeDriver();
    }
}
