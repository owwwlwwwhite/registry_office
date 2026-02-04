package org.example;

import org.apache.commons.io.FileUtils;
import org.example.pages.*;
import org.example.valueObjects.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckApplicationsByAdminTest extends BaseTest {
    private String createApplication(Mode mode, ServiceData serviceData) {
        mainPage.enterAsUser();
        new ApplicantDataPage(driver).fillFormAndSubmit(applicant);
        new ChoiceOfServicePage(driver).selectRegistration(mode);
        new CitizenDataPage(driver).fillFormAndSubmit(citizen);
        new ServiceDataPage(driver, mode).fillFormAndSubmit(serviceData);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WaitUtil.waitForVisible(driver, By.xpath("//span[contains(text(), 'отправлена на рассмотрение')]"), Duration.ofSeconds(5));
        WebElement spanWithApplicationNumber = driver.findElement(By.xpath("//span[contains(text(), 'отправлена на рассмотрение')]"));
        return spanWithApplicationNumber.getText().split(" ")[3];
    }

    private boolean applicationNumberIsExist(String applicationNumber) {
        driver.get(testProperties.getProperty("url"));

        new MainPage(driver).enterAsAdmin();
        new AdminRegistrationPage(driver).fillFormAndSubmit(adminData);
        return new ApplicationsAdministrationPage(driver).isApplicationNumberEqualsExpected(applicationNumber);
    }

    @Test
    void checkMarriageApplicationCreation() {
        String applicationNumber = createApplication(Mode.MARRIAGE, serviceMarriageData);

        assertTrue(applicationNumberIsExist(applicationNumber));
    }

    @Test
    void checkDeathApplicationCreation() {
        String applicationNumber = createApplication(Mode.DEATH, serviceDeathData);

        assertTrue(applicationNumberIsExist(applicationNumber));
    }

    @Test
    void checkBirthApplicationCreation() {
        String applicationNumber = createApplication(Mode.BIRTH, serviceBirthData);

        assertTrue(applicationNumberIsExist(applicationNumber));
    }
}
