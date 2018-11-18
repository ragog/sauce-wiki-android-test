package com.saucelabs.example;

import com.saucelabs.example.screen.HomePage;
import io.appium.java_client.AppiumDriver;

/**
 * Created by grago on 27.09.17.
 */
public class KBCWebsite {

    private AppiumDriver driver;

    public KBCWebsite(AppiumDriver driver) {
        this.driver = driver;
    }

    public HomePage homePage() { return new HomePage(driver); }

}
