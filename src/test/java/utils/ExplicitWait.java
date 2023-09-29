package utils;

import managment.WebDriverManager;
import models.ConfigModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class ExplicitWait {
    private static final Logger logger = LogManager.getLogger(ExplicitWait.class);
    private final WebDriverWait wait;

    private int timeForWait() {
        ConfigModel configData = ReadConfigProperties.getConfig();
        return configData.getTimeWait();
    }

    public ExplicitWait() throws IOException {
        WebDriver driver = WebDriverManager.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeForWait()));
        logger.info("Explicit wait instance created");
    }

    public void waitForPageLoad(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        logger.info("Page has loaded successfully");
    }

    public WebElement waitForPresenceOfElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        logger.info("Element " + locator.toString() + " is present");
        return element;
    }

    public void waitForPageLoad() {
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        logger.info("Page has loaded successfully");
    }

    public void waitForClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
