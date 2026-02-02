package org.example.pages;

import org.example.valueObjects.Mode;
import org.example.elements.Button;
import org.openqa.selenium.WebDriver;

public class ChoiceOfService {
    private final WebDriver driver;

    private final Button deathRegistrationBtn;
    private final Button birthRegistrationBtn;
    private final Button marriageRegistrationBtn;

    public ChoiceOfService(WebDriver driver) {
        this.driver = driver;
        deathRegistrationBtn = new Button(driver, "Регистрация смерти");
        marriageRegistrationBtn = new Button(driver, "Регистрация брака");
        birthRegistrationBtn = new Button(driver, "Регистрация рождения");
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
