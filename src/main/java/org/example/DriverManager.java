package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

    private static DriverManager instance;
    private WebDriver driver;

    public DriverManager() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void closeDriver() {
        if (driver != null) {
            driver.close();
            driver = null;
            instance = null;
        }
    }
}
