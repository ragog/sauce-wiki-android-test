package com.saucelabs.example;

import com.saucelabs.example.util.PropertyHelper;
import com.saucelabs.example.util.ResultReporter;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by grago on 27.09.17.
 */
public class AbstractTest {

    private AppiumDriver driver;
    protected Wikipedia wikipedia;
    private ResultReporter reporter;

    @BeforeMethod
    @Parameters({ "platformName", "platformVersion"})
    public void setup(String platformName, String platformVersion, Method method) throws MalformedURLException {

        String testName = method.getName();

        String apiKey =
                System.getenv("TESTOBJECT_API_KEY") != null ? System.getenv("TESTOBJECT_API_KEY") : PropertyHelper.getProperty("testobject.api.key");
        String testId = UUID.randomUUID().toString();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("testobject_api_key", apiKey);
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("name", testName);

        capabilities.setCapability("uuid", testId);

        driver = new AndroidDriver(new URL("https://eu1.appium.testobject.com/wd/hub"),
                capabilities);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wikipedia = new Wikipedia(driver);
        reporter = new ResultReporter();

    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        boolean success = result.isSuccess();
        String sessionId = driver.getSessionId().toString();

        reporter.saveTestStatus(sessionId, success);
        driver.quit();
    }

}
