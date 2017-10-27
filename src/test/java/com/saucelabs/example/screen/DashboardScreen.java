package com.saucelabs.example.screen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;

import java.util.NoSuchElementException;

/**
 * Created by grago on 25.10.17.
 */
public class DashboardScreen extends AbstractScreen {

    @AndroidFindBy(id = "org.wikipedia:id/menu_overflow_button")
    private MobileElement overflowButton;

    @AndroidFindBy(id = "org.wikipedia:id/explore_overflow_account_name")
    private MobileElement loginButton;

    @AndroidFindBy(id = "org.wikipedia:id/search_container")
    private MobileElement searchBar;

    public DashboardScreen(AppiumDriver driver) {
        super(driver);
    }

    public void navigateToLogin() {
        overflowButton.click();
        loginButton.click();
    }

    public boolean isActive() {
        try {
            return searchBar.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
