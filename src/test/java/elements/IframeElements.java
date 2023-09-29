package elements;

import base.BaseElement;
import managment.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ExplicitWait;
import org.openqa.selenium.By;


import java.io.IOException;

public class IframeElements extends BaseElement {
    private final ExplicitWait wait = new ExplicitWait();
    private final Logger logger = LogManager.getLogger(IframeElements.class);
    private final WebDriver driver;

    public IframeElements(By locator, String name) throws IOException {
        super(locator, name);
        driver = WebDriverManager.getDriver();
        logger.info("Iframe instance created");
    }

    public void changeToParentFrame() {
        wait.waitForPageLoad();
        String parentFrame = "frame1";
        driver.switchTo().frame(parentFrame);
    }

    public String getBodyTxt() {
        String text = getText(getLocator());
        driver.switchTo().defaultContent();
        return text;
    }

    public String getParentFrameTxt() {
        wait.waitForPageLoad();
        changeToParentFrame();
        String text = getBodyTxt();
        logger.info("Got text from parent frame: {}", text);
        return text;
    }

    private WebElement getChildrenIframeElement(By childIframeLocator) {
        return wait.waitForPresenceOfElement(childIframeLocator);
    }

    public String getChildIframeTxt(By childrenFrameLocator) {
        changeToParentFrame();
        WebElement childrenIframe = getChildrenIframeElement(childrenFrameLocator);
        driver.switchTo().frame(childrenIframe);
        String txt = getBodyTxt();
        logger.info("Get text from child iframe {}", txt);
        return txt;
    }

}
