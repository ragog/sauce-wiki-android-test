package com.saucelabs.example.screen;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class HomePage extends AbstractScreen {


    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public void doSomething() {
        driver.get("https://www.kbc.be/retail/en.html");
        takeScreenshot();
        driver.findElement(By.cssSelector("button.btn.btn--cookie-banner")).click();
        takeScreenshot();
        driver.findElement(By.cssSelector("i.icon.icon--menu")).click();
        takeScreenshot();
        driver.findElement(By.cssSelector("li.nav--main__list-item")).click();
        takeScreenshot();
    }

    public boolean pageTitleIsVisible() {
        return driver.findElement(By.cssSelector("li.crumb.crumb--last")).isDisplayed();
    }

}
