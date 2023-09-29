package tests;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.AlertWindow;
import utils.GenerateRandomText;

import java.io.IOException;


public class AlertsTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(AlertsTest.class);

    @Test
    public void AlertTest() throws IOException {
        logger.info("Starting Alerts Test");
        AlertWindow alertPage = new AlertWindow();

        Boolean mainPageOpen = mainPage.isMainPageOpen();
        Assert.assertTrue(mainPageOpen, "Main page is not open");
        logger.debug("Main page is open");

        mainPage.scrollDown();

        mainPage.goToFrameWinPage();

        boolean booleanAlertsPageOpen = alertPage.isAlertsPageOpen();
        Assert.assertTrue(booleanAlertsPageOpen, "Not in Alerts, Frame & Windows page");
        logger.debug("Alerts page is open");

        alertPage.scrollDown();
        alertsFrameWindows.clickAlertsMenuBtn();

        Assert.assertTrue(alertsFrameWindows.isAlertsFormDisplayed(), "Alerts form is not open");
        logger.debug("Alerts page is open");

        alertPage.clickSeeAlertsBtn();
        String alertTxt = alertPage.getAlertText();

        String testTxtButtonSeeAlert = testData.getBtnAlert();
        Assert.assertEquals(alertTxt, testTxtButtonSeeAlert, "Expected alert text doesn't match");
        logger.debug("Alert with text 'You clicked a button' is open");
        alertPage.acceptAlert();

        alertPage.clickConfirmBtn();
        String confirmTxt = alertPage.getAlertText();
        String testTxtConfirmBtn = testData.getBtnClick();
        Assert.assertEquals(confirmTxt, testTxtConfirmBtn, "Expected alert text doesn't match");
        logger.debug("Alert with text 'Do you confirm action?' is open");


        alertPage.acceptAlert();

        String testTxtAppearedTxt = testData.getAppearedTxt();
        String appearedTxt = alertPage.getConfirmResult();
        Assert.assertEquals(appearedTxt, testTxtAppearedTxt, "Expected alert text doesn't match");
        logger.debug("Alert with text 'Do you confirm action?' is closed and text 'You selected Ok' has appeared on the page");


        alertPage.clickPromtBoxBtn();

        String testTxtEnterName = testData.getEnterName();
        String alertPromptTxt = alertPage.getAlertText();
        Assert.assertEquals(alertPromptTxt, testTxtEnterName, "Expected alert text doesn't match");

        String randomTxt = GenerateRandomText.RandomTxtGenerator(testData.getLengthRandomText());
        alertPage.typeTextToAlert(randomTxt);
        alertPage.acceptAlert();
        String appearTxt = alertPage.getAppearedText();
        Assert.assertTrue(appearTxt.contains(randomTxt), "Text entered doesn't match with appeared text");
        logger.debug("Alert with text 'Please enter your name' is open");

        logger.info("Finishing Alerts Test");
    }

}
