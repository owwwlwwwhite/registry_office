package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Properties;

public class App
{
    static private WebDriver driver;

    public static void cool( String[] args )
    {
        initializeDriver();

        enterForm();
        applicantDataStep();
        selectServiceStep();
        citizenDataStep();
        serviceDataStep();

        assertApplication();

        driver.quit();
    }

    private static void assertApplication() {
        if (driver.findElement(new By.ByXPath("//span[text()='Спасибо за обращение!']")).isDisplayed()) {
            System.out.println("TEST: passed");
        } else {
            System.out.println("TEST: failed");
        }
    }

    private static void initializeDriver() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        Properties testProperties = new Properties();
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
    }


    private static void serviceDataStep() {
        WebElement deathDate = driver.findElement(new By.ByXPath("//label[contains(text(),'Дата смерти')]/../../input"));
        deathDate.sendKeys("06012026");

        WebElement deathAddress = driver.findElement(new By.ByXPath("//label[contains(text(),'Место смерти')]/../../input"));
        deathAddress.sendKeys("абвг");

        WebElement proceedBtn = driver.findElement(new By.ByXPath("//button[contains(text(),'Завершить')]"));
        proceedBtn.click();
    }

    private static void citizenDataStep() {
        WebElement surname = driver.findElement(new By.ByXPath("//label[contains(text(),'Фамилия')]/../../input"));
        surname.sendKeys("Фа");

        WebElement name = driver.findElement(new By.ByXPath("//label[contains(text(),'Имя')]/../../input"));
        name.sendKeys("Ба");

        WebElement patronymic = driver.findElement(new By.ByXPath("//label[contains(text(),'Отчество')]/../../input"));
        patronymic.sendKeys("Отчес");

        WebElement birthDate = driver.findElement(new By.ByXPath("//label[contains(text(),'Дата рождения')]/../../input"));
        birthDate.sendKeys("06012026");

        WebElement passportNumber = driver.findElement(new By.ByXPath("//label[contains(text(),'Номер паспорта')]/../../input"));
        passportNumber.sendKeys("123456");

        WebElement sex = driver.findElement(new By.ByXPath("//label[contains(text(),'Пол')]/../../input"));
        sex.sendKeys("муж");

        WebElement applicantAddress = driver.findElement(new By.ByXPath("//label[contains(text(),'Адрес прописки')]/../../input"));
        applicantAddress.sendKeys("абвг");

        WebElement proceedBtn = driver.findElement(new By.ByXPath("//button[contains(text(),'Далее')]"));
        proceedBtn.click();
    }

    private static void selectServiceStep() {
        WebElement deathRegistrationBtn = driver.findElement(new By.ByXPath("//button[contains(text(),'Регистрация смерти')]"));
        deathRegistrationBtn.click();
    }

    private static void enterForm() {
        WebElement enterAsUserBtn = driver.findElement(new By.ByXPath("//button[1][contains(text(),'Войти как пользователь')]"));
        enterAsUserBtn.click();
    }

    private static void applicantDataStep() {
        WebElement surname = driver.findElement(new By.ByXPath("//label[contains(text(),'Фамилия')]/../../input"));
        surname.sendKeys("Фа");

        WebElement name = driver.findElement(new By.ByXPath("//label[contains(text(),'Имя')]/../../input"));
        name.sendKeys("Ба");

        WebElement patronymic = driver.findElement(new By.ByXPath("//label[contains(text(),'Отчество')]/../../input"));
        patronymic.sendKeys("Отчес");

        WebElement phoneNumber = driver.findElement(new By.ByXPath("//label[contains(text(),'Телефон')]/../../input"));
        phoneNumber.sendKeys("3753134");

        WebElement passportNumber = driver.findElement(new By.ByXPath("//label[contains(text(),'Номер паспорта')]/../../input"));
        passportNumber.sendKeys("123456");

        WebElement applicantAddress = driver.findElement(new By.ByXPath("//label[contains(text(),'Адрес прописки')]/../../input"));
        applicantAddress.sendKeys("абвг");

        WebElement proceedBtn = driver.findElement(new By.ByXPath("//button[contains(text(),'Далее')]"));
        proceedBtn.click();
    }
}
