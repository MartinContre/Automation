package elements;

import base.BaseElement;
import managment.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ExplicitWait;

import java.io.IOException;

public class FramesElements extends BaseElement {
    private final ExplicitWait wait = new ExplicitWait();
    private final Logger logger = LogManager.getLogger(FramesElements.class);
    private final WebDriver driver;

    public FramesElements(By locator, String name) throws IOException {
        super(locator, name);
        driver = WebDriverManager.getDriver();
        logger.info("Frames instance created");
    }

    public String getTextFromFrame(By sampleHeading) {
        WebElement frame = findElementByLocator(getLocator());
        driver.switchTo().frame(frame);
        logger.info("Switching to frame: {}", getName());
        wait.waitForPresenceOfElement(sampleHeading);
        String txt = getText(sampleHeading);
        logger.info("Getting text from frame: {}", txt);
        driver.switchTo().defaultContent();
        return txt;
    }
}
