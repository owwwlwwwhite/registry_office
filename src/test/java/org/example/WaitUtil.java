package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

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

    public static WebElement waitForVisible(WebDriver driver, By locator, Duration timeout) {
        return getWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForVisible(WebDriver driver, WebElement element) {
        return getWait(driver).until(ExpectedConditions.visibilityOf(element));
    }

    public static boolean waitForTextPresent(WebDriver driver, By locator, String text) {
        return getWait(driver).until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public static boolean waitForTextPresent(WebDriver driver, WebElement element, String text) {
        return getWait(driver).until(ExpectedConditions.textToBePresentInElement(element, text));
    }
}