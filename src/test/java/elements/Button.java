package elements;

import base.BaseElement;
import managment.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ExplicitWait;

import java.io.IOException;

public class Button extends BaseElement {
    private static final Logger logger = LogManager.getLogger(Button.class);
    private final ExplicitWait wait;
    private final WebDriver driver;
    public Button(By locator, String name) throws IOException {
        super(locator, name);
        logger.info("Button instance created");
        driver = WebDriverManager.getDriver();
        wait = new ExplicitWait();
    }

    public void clickOnBtn() {
        try {
            wait.waitForClickable(getLocator());
            //click();
            driver.findElement(getLocator()).click();
            logger.info("Clicked on the button with locator: " + getName());
        } catch (Exception e) {
            logger.warn("Could not click on the button with locator: " + getName(), e);
        }

    }
}
