package com.saucelabs.example;

import com.saucelabs.example.util.ResultReporter;
import com.saucelabs.saucerest.SauceREST;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by grago on 27.09.17.
 */
public class AbstractTest {

    private AppiumDriver driver;
    protected KBCWebsite KBCWebsite;
    private ResultReporter reporter;

    private String sauceSeleniumURI = "@ondemand.saucelabs.com:443";
    private String buildTag = System.getenv("BUILD_TAG");
    private String username = System.getenv("SAUCE_USERNAME");
    private String accesskey = System.getenv("SAUCE_ACCESS_KEY");
    private String rdcApiKey = System.getenv("RDC_API_KEY");
    private SauceREST sauceRESTClient = new SauceREST(username, accesskey);

    @BeforeMethod
    @Parameters({ "deviceType", "platformName", "platformVersion", "deviceName" })
    public void setup(String deviceType, String platformName, String platformVersion, String deviceName, Method method) throws MalformedURLException {

        String testName = method.getName();

        String testId = UUID.randomUUID().toString();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        String gridEndpoint = "";

        if (deviceType.equals("real")) {

            capabilities.setCapability("testobject_api_key", rdcApiKey);
            capabilities.setCapability("phoneOnly", "true");
            if (!deviceName.isEmpty()) {
                capabilities.setCapability("deviceName", deviceName);
            }
            gridEndpoint = "https://eu1.appium.testobject.com/wd/hub";

        } else if (deviceType.equals("virtual")) {

            if (buildTag != null) {
                capabilities.setCapability("build", buildTag);
            }

            capabilities.setCapability("deviceName", deviceName);
            capabilities.setCapability("browserName", "chrome"); // TODO browsername currently hardcoded

            gridEndpoint = "https://" + username + ":" + accesskey + sauceSeleniumURI + "/wd/hub";

        }

        capabilities.setCapability("testobject_api_key", rdcApiKey);
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("phoneOnly", "true");
        capabilities.setCapability("name", testName);

        capabilities.setCapability("uuid", testId);

        driver = new AndroidDriver(new URL(gridEndpoint),
                capabilities);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        KBCWebsite = new KBCWebsite(driver);
        reporter = new ResultReporter();

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        String sessionId = driver.getSessionId().toString();
        Boolean status = result.isSuccess();
        Boolean isTOTest = driver.getCapabilities().getCapability("testobject_api_key") != null;

        if (isTOTest) {
            // TestObject REST API
            reporter = new ResultReporter();
            reporter.saveTestStatus(sessionId, status);

            try {
                driver.quit();
            } catch (WebDriverException e) {}

        } else { // test was run on Sauce
            // Sauce REST API (updateJob)
            Map<String, Object> updates = new HashMap<String, Object>();
            updates.put("passed", status);
            sauceRESTClient.updateJobInfo(sessionId, updates);
        }

    }

}
