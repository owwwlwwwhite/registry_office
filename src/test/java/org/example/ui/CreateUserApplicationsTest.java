package org.example.ui;

import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.example.test_data.DataFactory;
import org.example.ui.valueObjects.Mode;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Раздел Пользователя")
public class CreateUserApplicationsTest extends BaseTest {
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Link(url = "https://app.qase.io/project/ZDYS?suite=3&tab=&previewMode=side&case=478")
    @DisplayName("Проверка успешного заполнения заявки на регистрацию смерти")
    void RegisterDeathApplication() {
        mainPage.enterAsUser();
        applicantDataPage.fillFormAndSubmit(DataFactory.getApplicant());
        choiceOfServicePage.selectRegistration(Mode.DEATH);
        citizenDataPage.fillFormAndSubmit(DataFactory.getCitizen());
        deathServiceDataPage.fillFormAndSubmit(DataFactory.getServiceDeathData());
        assertEquals("Создать новую заявку", applicationStatusPage.getCreateNewApplicationBtnText());
    }

    @Test
    @Link(url = "https://app.qase.io/project/ZDYS?suite=3&tab=&previewMode=side&case=477")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка успешного заполнения заявки на регистрацию рождения")
    void registerBirthApplication() {
        mainPage.enterAsUser();
        applicantDataPage.fillFormAndSubmit(DataFactory.getApplicant());
        choiceOfServicePage.selectRegistration(Mode.BIRTH);
        citizenDataPage.fillFormAndSubmit(DataFactory.getCitizen());
        birthServiceDataPage.fillFormAndSubmit(DataFactory.getServiceBirthData());
        assertEquals("Создать новую заявку", applicationStatusPage.getCreateNewApplicationBtnText());
    }

    @Test
    @Link(url = "https://app.qase.io/project/ZDYS?suite=3&tab=properties&previewMode=side&case=476")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка успешного заполнения заявки на регистрацию брака")
    void registerMarriageApplication() {
        mainPage.enterAsUser();
        applicantDataPage.fillFormAndSubmit(DataFactory.getApplicant());
        choiceOfServicePage.selectRegistration(Mode.MARRIAGE);
        citizenDataPage.fillFormAndSubmit(DataFactory.getCitizen());
        marriageServiceDataPage.fillFormAndSubmit(DataFactory.getServiceMarriageData());
        assertEquals("Создать новую заявку", applicationStatusPage.getCreateNewApplicationBtnText());
    }
}
