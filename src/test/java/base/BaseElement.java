package base;

import managment.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ExplicitWait;

import java.io.IOException;

public abstract class BaseElement {
    private final WebDriver driver;
    private final By uniqueLocator;
    private final String elementName;
    private final ExplicitWait wait;
    private static final Logger logger = LogManager.getLogger(BaseElement.class);

    public BaseElement(By locator, String name) throws IOException {
        uniqueLocator = locator;
        elementName = name;
        driver = WebDriverManager.getDriver();
        wait = new ExplicitWait();
        logger.info("Base Element created");
    }

    public WebElement findElementByLocator(By locator) {
        wait.waitForPresenceOfElement(locator);
        logger.info("Element found with locator: {}", locator);
        return driver.findElement(locator);
    }

    public String getText(By locator) {
        wait.waitForPresenceOfElement(locator);
        String text = driver.findElement(locator).getText();
        logger.info("Text of element with locator {}: {}", locator, text);
        return text;    }

    public By getLocator() {
        return uniqueLocator;
    }

    public String getName() {
        return elementName;
    }
}
