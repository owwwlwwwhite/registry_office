package org.example.utils;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.regex.Pattern;

@Log4j2
public class WaitUtil {
    // private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);
    private static final Duration POLLING_INTERVAL = Duration.ofMillis(500);

    public static WebDriverWait getWait(WebDriver driver, Duration timeout) {
        return (WebDriverWait) new WebDriverWait(driver, timeout)
                .pollingEvery(POLLING_INTERVAL)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
    }

    public static void waitForVisible(WebDriver driver, By locator, Duration timeout) {
        getWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
        log.info("Get wait for visible for {} and duration of {}", locator, timeout);
    }

    public static void waitForVisible(WebDriver driver, WebElement element, Duration timeout) {
        getWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
        log.info("Get wait for visible for {} and duration of {}", element, timeout);
    }
    public static void waitForPatternConsists(WebDriver driver, By locator, String regex, Duration timeout) {
        new WebDriverWait(driver, timeout).until(d -> {
            WebElement span = driver.findElement(locator);
            return Pattern.matches(regex, span.getText());
        });
        log.info("Get wait for application number load by locator {} and duration of {}", locator, timeout);
    }
}