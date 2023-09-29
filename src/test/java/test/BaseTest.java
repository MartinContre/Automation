package test;

import aquality.selenium.core.utilities.ISettingsFile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.BrowserUtils;
import utils.SettingsFilesUtils;
import utils.UriCreator;

public abstract class BaseTest {
    protected static final ISettingsFile TEST_DATA = SettingsFilesUtils.getTestDataFile();
    protected static final String DEFAULT_URL = UriCreator.getUri();

    @BeforeMethod
    public void setUp() {
        BrowserUtils.goToAndMaximize(DEFAULT_URL);
    }

    @AfterMethod
    public void tearDown() {
        BrowserUtils.closeBrowser();
    }
}
