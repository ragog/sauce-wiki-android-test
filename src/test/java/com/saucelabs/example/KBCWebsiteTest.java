package com.saucelabs.example;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class KBCWebsiteTest extends AbstractTest {

    @Test
    public void verifySectionTitle() {

        KBCWebsite.homePage().doSomething();
        Assert.assertTrue(KBCWebsite.homePage().pageTitleIsVisible());

    }

}
