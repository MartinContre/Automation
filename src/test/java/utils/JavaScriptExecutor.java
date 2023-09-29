package utils;

import managment.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class JavaScriptExecutor {
    private static final Logger logger = LogManager.getLogger(JavaScriptExecutor.class);
    private final WebDriver driver;

    public JavaScriptExecutor() throws IOException {
        this.driver = WebDriverManager.getInstance().getDriver();
        logger.info("Initialized Java Script Executor");
    }

    public void scrollDown() {
        logger.info("Scrolling down te page");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)","");
    }
}
