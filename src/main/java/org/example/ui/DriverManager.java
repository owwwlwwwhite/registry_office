package org.example.ui;

import io.qameta.allure.Allure;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class DriverManager {
    private static volatile DriverManager instance;
    private RemoteWebDriver driver;

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
            String browserName = System.getProperty("browser", "chrome");
            String browserVersion = System.getProperty("browser.version", "120.0");
            String selenoidUrl = System.getProperty("selenoid.url", "http://localhost:4444/wd/hub");

            MutableCapabilities capabilities = new MutableCapabilities();
            capabilities.setCapability("browserName", browserName);
            capabilities.setCapability("browserVersion", browserVersion);

            Map<String, Object> selenoidOptions = new HashMap<>();
            selenoidOptions.put("enableVNC", true);
            selenoidOptions.put("enableLog", true);
            selenoidOptions.put("sessionTimeout", "3m");

            capabilities.setCapability("selenoid:options", selenoidOptions);

            capabilities.setCapability("name", "Test on " + browserName);

            driver = new RemoteWebDriver(
                    URI.create(selenoidUrl).toURL(),
                    capabilities
            );

            Allure.parameter("Browser", browserName);
            Allure.parameter("Browser Version", browserVersion);

            log.info("WebDriver создан: browser={}, version={}, selenoid={}",
                    browserName, browserVersion, selenoidUrl);

        } catch (MalformedURLException e) {
            log.fatal("Неверный URL Selenoid: {}", e.getMessage());
            throw new RuntimeException("Invalid Selenoid URL", e);
        } catch (SessionNotCreatedException e) {
            log.fatal("Не удалось создать сессию в Selenoid: {}. Проверьте: 1) запущен ли Selenoid, 2) доступна ли версия браузера в browser.json",
                    e.getMessage());
            throw e;
        } catch (Exception e) {
            log.fatal("Неожиданная ошибка при инициализации драйвера: {}", e.getMessage());
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