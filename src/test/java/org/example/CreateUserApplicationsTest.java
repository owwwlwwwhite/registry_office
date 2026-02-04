package org.example;

import org.example.elements.Button;
import org.example.pages.*;
import org.example.valueObjects.Mode;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateUserApplicationsTest extends BaseTest {
    @Test
    void RegisterDeathApplication() {
        System.out.println("Enter 1 test");
        mainPage.enterAsUser();
        new ApplicantDataPage(driver).fillFormAndSubmit(applicant);
        new ChoiceOfServicePage(driver).selectRegistration(Mode.DEATH);
        new CitizenDataPage(driver).fillFormAndSubmit(citizen);
        new ServiceDataPage(driver, Mode.DEATH).fillFormAndSubmit(serviceDeathData);
        assertEquals("Создать новую заявку", driver.findElement(new By.ByXPath("//button[text()='Создать новую заявку']")).getText());
    }

    @Test
    void registerBirthApplication() {
        mainPage.enterAsUser();
        new ApplicantDataPage(driver).fillFormAndSubmit(applicant);
        new ChoiceOfServicePage(driver).selectRegistration(Mode.BIRTH);
        new CitizenDataPage(driver).fillFormAndSubmit(citizen);
        new ServiceDataPage(driver, Mode.BIRTH).fillFormAndSubmit(serviceBirthData);
        assertEquals("Создать новую заявку", driver.findElement(new By.ByXPath("//button[text()='Создать новую заявку']")).getText());
    }

    @Test
    void registerMarriageApplication() {
        mainPage.enterAsUser();
        new ApplicantDataPage(driver).fillFormAndSubmit(applicant);
        new ChoiceOfServicePage(driver).selectRegistration(Mode.MARRIAGE);
        new CitizenDataPage(driver).fillFormAndSubmit(citizen);
        new ServiceDataPage(driver, Mode.MARRIAGE).fillFormAndSubmit(serviceMarriageData);
        assertEquals("Создать новую заявку", driver.findElement(new By.ByXPath("//button[text()='Создать новую заявку']")).getText());
    }
}
