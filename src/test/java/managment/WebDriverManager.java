package managment;


import browser.DriverManager;
import browser.BrowserFactory;
import models.ConfigModel;
import org.openqa.selenium.WebDriver;
import utils.ReadConfigProperties;

import java.io.IOException;


public class WebDriverManager {

    private static WebDriverManager instance = null;
    private final WebDriver driver;
    private final DriverManager driverManager;

    private String getBrowserName() {
        ConfigModel configData = ReadConfigProperties.getConfig();
        return configData.getBrowser();

    }

    private WebDriverManager() {
        driverManager = BrowserFactory.getManager(getBrowserName());
        driver = driverManager.getDriver();
    }

    public static synchronized WebDriverManager getInstance() {
        if (instance == null) {
            instance = new WebDriverManager();
        }
        return instance;
    }

    public static WebDriver getDriver() throws IOException {
        return getInstance().driver;
    }

    public void closeAll() {
        driverManager.quitDriver();
    }
}
