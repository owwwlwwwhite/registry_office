package org.example.pages;

import org.example.GlobalConstants;
import org.example.valueObjects.Mode;
import org.example.elements.Button;
import org.openqa.selenium.WebDriver;

public class ChoiceOfService extends GlobalConstants {
    private final Button deathRegistrationBtn;
    private final Button birthRegistrationBtn;
    private final Button marriageRegistrationBtn;

    public ChoiceOfService(WebDriver driver) {
        deathRegistrationBtn = new Button(driver, DEATH_REGISTRATION_BTN);
        marriageRegistrationBtn = new Button(driver, MARRIAGE_REGISTRATION_BTN);
        birthRegistrationBtn = new Button(driver, BIRTH_REGISTRATION_BTN);
    }

    public void selectRegistration(Mode reg) {
        switch (reg) {
            case DEATH:
                deathRegistrationBtn.click();
                break;
            case BIRTH:
                birthRegistrationBtn.click();
                break;
            case MARRIAGE:
                marriageRegistrationBtn.click();
                break;
        }
    }
}
