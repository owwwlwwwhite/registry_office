package org.example;

import org.example.utils.DataFactoryUtil;
import org.example.valueObjects.Mode;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Раздел Пользователя")
public class CreateUserApplicationsTest extends BaseTest {
    @Test
    @DisplayName("Проверка успешного заполнения заявки на регистрацию смерти")
    void RegisterDeathApplication() {
        mainPage.enterAsUser();
        applicantDataPage.fillFormAndSubmit(DataFactoryUtil.getApplicant());
        choiceOfServicePage.selectRegistration(Mode.DEATH);
        citizenDataPage.fillFormAndSubmit(DataFactoryUtil.getCitizen());
        deathServiceDataPage.fillFormAndSubmit(DataFactoryUtil.getServiceDeathData());
        assertEquals("Создать новую заявку", applicationStatusPage.getCreateNewApplicationBtnText());
    }

    @Test
    @DisplayName("Проверка успешного заполнения заявки на регистрацию рождения")
    void registerBirthApplication() {
        mainPage.enterAsUser();
        applicantDataPage.fillFormAndSubmit(DataFactoryUtil.getApplicant());
        choiceOfServicePage.selectRegistration(Mode.BIRTH);
        citizenDataPage.fillFormAndSubmit(DataFactoryUtil.getCitizen());
        birthServiceDataPage.fillFormAndSubmit(DataFactoryUtil.getServiceBirthData());
        assertEquals("Создать новую заявку", applicationStatusPage.getCreateNewApplicationBtnText());
    }

    @Test
    @DisplayName("Проверка успешного заполнения заявки на регистрацию брака")
    void registerMarriageApplication() {
        mainPage.enterAsUser();
        applicantDataPage.fillFormAndSubmit(DataFactoryUtil.getApplicant());
        choiceOfServicePage.selectRegistration(Mode.MARRIAGE);
        citizenDataPage.fillFormAndSubmit(DataFactoryUtil.getCitizen());
        marriageServiceDataPage.fillFormAndSubmit(DataFactoryUtil.getServiceMarriageData());
        assertEquals("Создать новую заявку", applicationStatusPage.getCreateNewApplicationBtnText());
    }
}
