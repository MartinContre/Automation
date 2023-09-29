package elements;

import base.BaseElement;
import managment.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class Alerts extends BaseElement {
    private final Logger logger = LogManager.getLogger(Alerts.class);
    WebDriver driver;

    public Alerts(By locator, String name) throws IOException {
        super(locator, name);
        driver = WebDriverManager.getDriver();
        logger.info("Alerts instance created");
    }

    public String getAlertText() {
        String text = driver.switchTo().alert().getText();
        logger.info("Getting text from alert: " + getName());
        return text;
    }

    public void acceptAlert() {
        logger.info("Alert was accept");
        driver.switchTo().alert().accept();
    }

    public void typeIntoAlert(String txt) {
        logger.info("Text " + txt + " was wrote");
        driver.switchTo().alert().sendKeys(txt);
    }

    public String getTextFromAlerts() {
        String txt = getText(getLocator());
        logger.info("Getting text from alert: " + getName());
        return txt;
    }

}
