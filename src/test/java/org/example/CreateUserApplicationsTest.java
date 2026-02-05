package org.example;

import org.example.pages.*;
import org.example.utils.DataProviderUtil;
import org.example.valueObjects.Mode;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateUserApplicationsTest extends BaseTest {
    @Test
    void RegisterDeathApplication() {
        mainPage.enterAsUser();
        new ApplicantDataPage(driver).fillFormAndSubmit(DataProviderUtil.getApplicant());
        new ChoiceOfServicePage(driver).selectRegistration(Mode.DEATH);
        new CitizenDataPage(driver).fillFormAndSubmit(DataProviderUtil.getCitizen());
        new ServiceDataPage(driver, Mode.DEATH).fillFormAndSubmit(DataProviderUtil.getServiceDeathData());
        ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage(driver);
        assertEquals("Создать новую заявку", applicationStatusPage.getCreateNewApplicationBtn().getText());
    }

    @Test
    void registerBirthApplication() {
        mainPage.enterAsUser();
        new ApplicantDataPage(driver).fillFormAndSubmit(DataProviderUtil.getApplicant());
        new ChoiceOfServicePage(driver).selectRegistration(Mode.BIRTH);
        new CitizenDataPage(driver).fillFormAndSubmit(DataProviderUtil.getCitizen());
        new ServiceDataPage(driver, Mode.BIRTH).fillFormAndSubmit(DataProviderUtil.getServiceBirthData());
        ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage(driver);
        assertEquals("Создать новую заявку", applicationStatusPage.getCreateNewApplicationBtn().getText());
    }

    @Test
    void registerMarriageApplication() {
        mainPage.enterAsUser();
        new ApplicantDataPage(driver).fillFormAndSubmit(DataProviderUtil.getApplicant());
        new ChoiceOfServicePage(driver).selectRegistration(Mode.MARRIAGE);
        new CitizenDataPage(driver).fillFormAndSubmit(DataProviderUtil.getCitizen());
        new ServiceDataPage(driver, Mode.MARRIAGE).fillFormAndSubmit(DataProviderUtil.getServiceMarriageData());
        ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage(driver);
        assertEquals("Создать новую заявку", applicationStatusPage.getCreateNewApplicationBtn().getText());
    }
}
