package org.example.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.regex.Pattern;

public class WaitUtil {

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
    }

    public static void waitForVisible(WebDriver driver, WebElement element, Duration timeout) {
        getWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForPresenceOfElement(WebDriver driver, By locator, Duration timeout) {
        return getWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitForApplicationNumberLoad(WebDriver driver, By locator, Duration timeout) {
        new WebDriverWait(driver, timeout).until(d -> {
            WebElement span = driver.findElement(locator);
            return Pattern.matches(".*\\d+.*", span.getText());
        });
    }
}