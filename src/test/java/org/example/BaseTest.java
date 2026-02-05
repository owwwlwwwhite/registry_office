package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.*;
import org.example.valueObjects.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected Properties testProperties;

    protected MainPage mainPage;

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
