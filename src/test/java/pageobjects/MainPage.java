package pageobjects;

import base.BaseForm;
import elements.Button;
import org.openqa.selenium.By;
import utils.ExplicitWait;

import java.io.IOException;

public class MainPage extends BaseForm {

    private final By mainImageLocator = By.xpath("//img[@class='banner-image' and @alt='Selenium Online Training']");
    private final By alertsFrameWindowsBtn = By.xpath("//h5[text()='Alerts, Frame & Windows']");
    private final By elementBtn = By.xpath("//h5[text()='Elements']");
    private final ExplicitWait explicitWait = new ExplicitWait();
    public MainPage() throws IOException {
    }

    public Boolean isMainPageOpen() {
        explicitWait.waitForPageLoad(mainImageLocator);
        return isFormOpen(mainImageLocator);
    }

    public void goToFrameWinPage() throws IOException {
        explicitWait.waitForPageLoad(alertsFrameWindowsBtn);
        Button btn = new Button(alertsFrameWindowsBtn, "Alerts frame windows button");
        btn.clickOnBtn();
    }

    public void goToElementBtn() throws IOException {
        explicitWait.waitForPageLoad(elementBtn);
        Button btn = new Button(elementBtn, "Element button");
        btn.clickOnBtn();
    }

}
