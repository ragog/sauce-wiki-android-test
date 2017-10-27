package com.saucelabs.example;

import com.saucelabs.example.screen.DashboardScreen;
import com.saucelabs.example.screen.LoginScreen;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;

/**
 * Created by grago on 27.09.17.
 */
public class Wikipedia {

    private AppiumDriver driver;

    public Wikipedia(AppiumDriver driver) {
        this.driver = driver;
    }

    public DashboardScreen dashboardScreen() { return new DashboardScreen(driver); }
    public LoginScreen loginScreen() { return new LoginScreen(driver); }

}
