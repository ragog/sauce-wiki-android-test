package com.saucelabs.example;

import com.saucelabs.example.data.Credentials;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.assertTrue;

public class WikiAndroidTest extends AbstractTest {

    @Test
    public void loginWithValidCredentials() {

        wikipedia.dashboardScreen().navigateToLogin();
        wikipedia.loginScreen().performLogin(Credentials.USER_VALID);
        Assert.assertTrue(wikipedia.dashboardScreen().isActive());

    }

}
