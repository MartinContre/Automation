package base;

import managment.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.JavaScriptExecutor;

import java.io.IOException;

public class BaseForm {
    protected WebDriver driver;
    protected JavaScriptExecutor jsExecutor;
    private static final Logger logger = LogManager.getLogger(BaseForm.class);

    public BaseForm() throws IOException {
        driver = WebDriverManager.getDriver();
        jsExecutor = new JavaScriptExecutor();
    }

    public void scrollDown() {
        jsExecutor.scrollDown();
        logger.debug("Scrolled down");
    }

    public boolean isFormOpen(By locator) {
        try {
            boolean isOpen = driver.findElement(locator).isDisplayed();
            if (isOpen) {
                logger.info("Form with locator '{}' is open", locator);
            } else {
                logger.warn("Form with locator '{}' is not open", locator);
            }
            return isOpen;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.error("Form with locator '{}' was not found", locator);
            return false;
        }
    }

    public void closePage() {
        driver.close();
        logger.debug("Closed driver");
    }

    public void openPage(String url) {
        driver.get(url);
        logger.info("Opened page whit url '{}'", url);
    }
}
