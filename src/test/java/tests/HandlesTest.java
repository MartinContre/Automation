package tests;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.BrowserWindows;
import pageobjects.ElementsWindow;
import pageobjects.LinksPage;
import utils.ChangeHandler;

import java.io.IOException;

public class HandlesTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(HandlesTest.class);

    @Test
    public void handlesTest() throws IOException {
        BrowserWindows browserWindows = new BrowserWindows();
        ChangeHandler changeHandler = new ChangeHandler();
        ElementsWindow elementsWindow = new ElementsWindow();
        LinksPage linksWindow = new LinksPage();

        logger.info("Starting Handles Test");

        Boolean mainPageOpen = mainPage.isMainPageOpen();
        Assert.assertTrue(mainPageOpen, "Main page is not open");
        logger.debug("Main page is open");

        mainPage.scrollDown();

        mainPage.goToFrameWinPage();

        alertsFrameWindows.scrollDown();

        alertsFrameWindows.clickBrowserWindowsMenuBtn();

        Assert.assertTrue(browserWindows.isBrowserWindowsOpen(), "Browser windows is not open");
        logger.debug("Browser Windows page is open");

        browserWindows.clickNewTabBtn();

        changeHandler.changeHandler();
        String headingTxt = changeHandler.getNewPageTxt();
        String headingTxtExpected = testData.getNewTabTxt();
        Assert.assertEquals(headingTxt, headingTxtExpected, "New tab with sample page was no open");

        browserWindows.closeSamplePage();

        changeHandler.returnToOriginalWindow();

        Assert.assertTrue(browserWindows.isBrowserWindowsOpen(), "Browser windows is not open");
        logger.debug("Browser Windows is open");

        browserWindows.clickElementsBtnOnLeft();

        elementsWindow.scrollDown();
        elementsWindow.clickLinksBtn();

        Assert.assertTrue(linksWindow.isLinkPageOpen(), "Links page not open");
        logger.debug("Links Window is open");

        linksWindow.clickHomeLink();

        changeHandler.changeHandler();
        Assert.assertTrue(mainPage.isMainPageOpen(), "Main Page not open");
        logger.debug("Main page is open");

        changeHandler.returnToOriginalWindow();

        Assert.assertTrue(linksWindow.isLinkPageOpen(), "Links page not open");
        logger.debug("Links window page is open");

        logger.info("Finishing Handles Test");
    }

}
