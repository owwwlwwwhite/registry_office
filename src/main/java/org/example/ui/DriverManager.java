package org.example.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Log4j2
public class DriverManager {
    private static volatile DriverManager instance;
    private WebDriver driver;

    private DriverManager() {
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            synchronized (DriverManager.class) {
                if (instance == null) {
                    instance = new DriverManager();
                }
            }
        }
        return instance;
    }

    public synchronized WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    private void initializeDriver() {
        try {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
             options.addArguments("--headless=new");
            // options.addArguments("--disable-gpu", "--no-sandbox");

            driver = new ChromeDriver(options);
//            driver.manage().window().maximize();
            log.info("Chrome Driver создан/пересоздан");
        } catch (SessionNotCreatedException e) {
            log.fatal("Не удалось создать ChromeDriver: {}", e.getMessage());
            throw e;
        }
    }

    public synchronized void closeDriver() {
        if (driver != null) {
            try {
                driver.quit();
                log.info("WebDriver сессия закрыта");
            } catch (Exception e) {
                log.error("Ошибка при закрытии драйвера: {}", e.getMessage());
            } finally {
                driver = null;
            }
        }
    }
}