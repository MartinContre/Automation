package utils;

import base.BaseElement;
import managment.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class ChangeHandler extends BaseElement {
    private static final Logger logger = LogManager.getLogger(ChangeHandler.class);
    private final ExplicitWait wait = new ExplicitWait();
    private final By sampleHeadingLocator = By.id("sampleHeading");
    private final WebDriver driver;
    private String originalWindow;

    public ChangeHandler() throws IOException {
        super(null, null);
        driver = WebDriverManager.getDriver();

    }

    public void changeHandler() {
        originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                logger.info("Switched to new window: " + windowHandle);
                break;
            }
        }
    }

    public String getNewPageTxt() {
        wait.waitForPresenceOfElement(sampleHeadingLocator);
        return findElementByLocator(sampleHeadingLocator).getText();
    }

    public void returnToOriginalWindow() {
        logger.debug("Returning to original window");
        driver.switchTo().window(originalWindow);
    }

}
