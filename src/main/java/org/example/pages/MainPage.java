package org.example.pages;

import org.example.GlobalConstants;
import org.example.elements.Button;
import org.openqa.selenium.WebDriver;

public class MainPage extends GlobalConstants {

    private final Button enterAsUserButton;
    private final Button enterAsAdminButton;

    public MainPage(WebDriver driver) {
        enterAsUserButton = new Button(driver, ENTER_AS_USER_BUTTON);
        enterAsAdminButton = new Button(driver, ENTER_AS_ADMIN_BUTTON);
    }

    public void enterAsUser() {
        enterAsUserButton.click();
    }
    public void enterAsAdmin() {
        enterAsAdminButton.click();
    }
}
