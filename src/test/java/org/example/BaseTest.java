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
    protected static WebDriver driver;
    protected static Properties testProperties;

    protected MainPage mainPage;

    protected final Applicant applicant = new Applicant("Фа", "Им", "Отчес", "1234567", "123456", "абвг");
    protected final Citizen citizen = new Citizen("Фа", "Им", "Отчес", "01062026", "муж", "123456", "абвг");
    protected final ServiceData serviceDeathData = new ServiceData
            .Builder()
            .setDeathData("01.01.2000", "абвг")
            .build();
    protected final ServiceData serviceMarriageData = new ServiceData
            .Builder()
            .setMarriageData("01012000",
                    "фф",
                    "фф",
                    "фф",
                    "ффыыв",
                    "01011995",
                    "123456")
            .build();
    protected final ServiceData serviceBirthData = new ServiceData
            .Builder()
            .setBirthData("абвг", "аa", "аа", "аа", "аа")
            .build();
    protected final Admin adminData = new Admin(
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

        mainPage = new MainPage(driver);
    }

    @AfterAll
    static void exit() {
        DriverManager.getInstance().closeDriver();
    }

}
