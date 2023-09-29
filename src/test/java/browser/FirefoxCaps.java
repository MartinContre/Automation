package browser;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxCaps extends DriverManager {

    @Override
    public void createDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-private");
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
    }
}
