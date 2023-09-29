package test;

import api.ApiUtils;
import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    protected static final ISettingsFile CONFIG_DATA_READER = new JsonSettingsFile("config_data.json");
    private static final String EURONEWS_URL = CONFIG_DATA_READER.getValue("/euro-news-url").toString();


    @BeforeMethod
    protected void beforeMethod() {
        ApiUtils.markAllMessagesRead();
        getBrowser().goTo(EURONEWS_URL);
        getBrowser().waitForPageToLoad();
        getBrowser().maximize();
    }

    @AfterMethod
    protected void afterMethod() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }

    protected Browser getBrowser() {
        return AqualityServices.getBrowser();
    }
}
