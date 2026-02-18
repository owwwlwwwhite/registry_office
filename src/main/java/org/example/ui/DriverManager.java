package org.example.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@Log4j2
public class DriverManager {

    private static DriverManager instance;
    @Getter
    private WebDriver driver;

    public DriverManager() {
        WebDriverManager.chromedriver().setup();
        try {
            driver = new ChromeDriver();
        } catch (SessionNotCreatedException e) {
            log.fatal(e);
            throw e;
        }
        log.info("Create new Chrome Driver");
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    public void closeDriver() {
        if (driver != null) {
            driver.close();
            driver = null;
            instance = null;
        }
        log.info("Close driver");
    }
}
