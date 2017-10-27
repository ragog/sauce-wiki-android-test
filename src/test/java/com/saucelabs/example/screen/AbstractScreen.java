package com.saucelabs.example.screen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by grago on 27.09.17.
 */
public class AbstractScreen {

    protected AppiumDriver driver;

    public AbstractScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, 15, TimeUnit.SECONDS), this);
    }

    public void takeScreenshot() {
        driver.getScreenshotAs(OutputType.BASE64);
    }

}
