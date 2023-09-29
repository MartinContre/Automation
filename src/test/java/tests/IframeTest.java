package tests;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.Frames;
import pageobjects.Iframe;

import java.io.IOException;

public class IframeTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(IframeTest.class);

    @Test
    public void testIframe() throws IOException {
        Frames frames = new Frames();
        Iframe iframe = new Iframe();

        logger.info("Starting Iframe Test");

        Boolean mainPageOpen = mainPage.isMainPageOpen();
        Assert.assertTrue(mainPageOpen, "Main page is not open");
        logger.debug("Main page is open");

        mainPage.scrollDown();

        mainPage.goToFrameWinPage();

        alertsFrameWindows.scrollDown();

        alertsFrameWindows.clickNestedFramesMenuBtn();

        Assert.assertTrue(iframe.isNestedFramesDisplayed(), "Nested frames is not opened");
        logger.debug("Nested Frames is open");


        String txtExpectedParent = testData.getParentTxt();
        String parentFrameTxt = iframe.getParentFrameTxt();
        Assert.assertEquals(parentFrameTxt, txtExpectedParent, "Parent frame message does not match");


        String txtExpectedChild = testData.getChildTxt();
        String childIframeTxt = iframe.getChildrenIframeTxt();
        Assert.assertEquals(childIframeTxt, txtExpectedChild, "Child Iframe message does not match");

        iframe.scrollDown();

        alertsFrameWindows.clickOnFramesBtn();

        Assert.assertTrue(frames.isFramesDisplayed(), "Frames form is not open");

        String upperFrameTxt = frames.getTextFrame1();
        frames.scrollDown();
        String lowerFrameTxt = frames.getTextFrame2();
        Assert.assertEquals(upperFrameTxt, lowerFrameTxt, "Text from frames does not match");

        logger.info("Finishing Iframe Test");

    }

}
