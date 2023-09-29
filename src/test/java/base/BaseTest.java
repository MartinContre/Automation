package base;

import managment.WebDriverManager;
import models.ConfigModel;
import models.TestData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pageobjects.AlertsFrameWindowsWindow;
import pageobjects.MainPage;
import utils.ReadConfigProperties;
import utils.TestDataReader;

import java.io.IOException;

public class BaseTest {
    private final ConfigModel data = ReadConfigProperties.getConfig();
    public static MainPage mainPage;
    public static AlertsFrameWindowsWindow alertsFrameWindows;
    public static TestData testData;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    @BeforeTest
    public void setUp() throws IOException {
        logger.info("Starting test configuration");
        Assert.assertNotNull(data.getBrowser(), "Cannot get browser name");
        Assert.assertNotNull(data.getTestURL(), "Cannot get URL");
        Assert.assertNotNull(data.getPathDataTest(), "Cannot get path to the data");
        Assert.assertTrue(data.getTimeWait() >= 0, "Cannot get time for waits");

        String url = data.getTestURL();
        String pathDataJson = data.getPathDataTest();

        mainPage = new MainPage();
        alertsFrameWindows = new AlertsFrameWindowsWindow();

        mainPage.openPage(url);

        testData = new TestDataReader(pathDataJson).getConfig();
        Assert.assertNotNull(testData.getBtnAlert(), "Cannot get text from alert");
        Assert.assertNotNull(testData.getBtnClick(), "Cannot get text when you click a button");
        Assert.assertNotNull(testData.getAppearedTxt(), "Cannot get appeared text");
        Assert.assertNotNull(testData.getEnterName(), "Cannot get text \"Please enter your name\"");
        Assert.assertTrue(testData.getLengthRandomText() >= 0, "Cannot get length to generate random text");
        Assert.assertNotNull(testData.getParentTxt(), "Cannot get parent text");
        Assert.assertNotNull(testData.getChildTxt(), "Cannot get child text");
        Assert.assertNotNull(testData.getNewTabTxt(), "Cannot get text in nwe window");
        Assert.assertTrue(testData.getRowsTable() >=0, "Cannot get number of rows");
        Assert.assertNotNull(testData.getEmployees(), "Cannot get employees");
    }

    @AfterTest
    public void tearDown() {
        logger.info("Finishing test and closing resources");
        WebDriverManager.getInstance().closeAll();

    }
}
