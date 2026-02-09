package org.example.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.regex.Pattern;

public class WaitUtil {

    public static final Logger waitLogger = LogManager.getLogger(WaitUtil.class);

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);
    private static final Duration POLLING_INTERVAL = Duration.ofMillis(500);

    public static WebDriverWait getWait(WebDriver driver) {
        return getWait(driver, DEFAULT_TIMEOUT);
    }

    public static WebDriverWait getWait(WebDriver driver, Duration timeout) {
        return (WebDriverWait) new WebDriverWait(driver, timeout)
                .pollingEvery(POLLING_INTERVAL)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
    }

    public static void waitForVisible(WebDriver driver, By locator, Duration timeout) {
        getWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
        waitLogger.info("Get wait for visible for {} and duration of {}", locator, timeout);
    }

    public static void waitForVisible(WebDriver driver, WebElement element, Duration timeout) {
        getWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
        waitLogger.info("Get wait for visible for {} and duration of {}", element, timeout);
    }

    public static WebElement waitForPresenceOfElement(WebDriver driver, By locator, Duration timeout) {
        return getWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitForApplicationNumberLoad(WebDriver driver, By locator, Duration timeout) {
        new WebDriverWait(driver, timeout).until(d -> {
            WebElement span = driver.findElement(locator);
            return Pattern.matches(".*\\d+.*", span.getText());
        });
        waitLogger.info("Get wait for application number load by locator {} and duration of {}", locator, timeout);
    }
}