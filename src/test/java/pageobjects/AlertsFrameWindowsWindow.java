package pageobjects;

import base.BaseForm;
import elements.Button;
import org.openqa.selenium.By;

import java.io.IOException;

public class AlertsFrameWindowsWindow extends BaseForm {
    private final By alertsBtnLocator = By.xpath("//span[text()='Alerts']");
    private final By browserWindowsBtnLocator = By.xpath("//span[text()='Browser Windows']");
    private final By nestedFramesBtnLocator = By.xpath("//span[text()='Nested Frames']");
    private final By framesBtnLocator = By.xpath("//span[text()='Frames']");
    private final By alertsHeaderLocator = By.className("main-header");
    public AlertsFrameWindowsWindow() throws IOException {
    }

    public void clickAlertsMenuBtn() throws IOException {
        Button btn = new Button(alertsBtnLocator, "Alerts button");
        btn.clickOnBtn();
    }

    public void clickBrowserWindowsMenuBtn() throws IOException {
        Button btn = new Button(browserWindowsBtnLocator, "Browser Windows button");
        btn.clickOnBtn();
    }

    public void clickNestedFramesMenuBtn() throws IOException {
        Button btn = new Button(nestedFramesBtnLocator, "Nested Frames button");
        btn.clickOnBtn();
    }

    public boolean isAlertsFormDisplayed() {
        return isFormOpen(alertsHeaderLocator);
    }

    public void clickOnFramesBtn() throws IOException {
        Button btn = new Button(framesBtnLocator, "Frames button");
        btn.clickOnBtn();
    }
}
