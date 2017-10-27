package com.saucelabs.example.screen;

import com.saucelabs.example.data.Credentials;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;

/**
 * Created by grago on 25.10.17.
 */
public class LoginScreen extends AbstractScreen {

    @AndroidFindBy(id = "org.wikipedia:id/login_username_text")
    private MobileElement usernameField;

    @AndroidFindBy(id = "org.wikipedia:id/login_password_input")
    private MobileElement passwordField;

    @AndroidFindBy(id = "org.wikipedia:id/login_button")
    private MobileElement loginButton;

    public LoginScreen(AppiumDriver driver) {
        super(driver);
    }

    public void performLogin(Credentials credentials) {
        usernameField.sendKeys(credentials.username);
        passwordField.sendKeys(credentials.password);
        loginButton.click();
    }

}
