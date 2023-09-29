package pageobjects;

import base.BaseForm;
import elements.Alerts;
import elements.Button;
import org.openqa.selenium.By;

import java.io.IOException;


public class AlertWindow extends BaseForm {
    private final By mainHeaderLocator = By.className("main-header");
    private final By seeAlertBtnLocator = By.id("alertButton");
    private final By confirmBtnLocator = By.id("confirmButton");
    private final By confirmResultLocator = By.id("confirmResult");
    private final By promtBtnLocator = By.id("promtButton");
    private final By textSentLocator = By.id("promptResult");

    public AlertWindow() throws IOException {
    }

    public boolean isAlertsPageOpen() {
        return isFormOpen(mainHeaderLocator);
    }

    public void clickSeeAlertsBtn() throws IOException {
        Button btn = new Button(seeAlertBtnLocator, "Alert button");
        btn.clickOnBtn();
    }

    public void clickConfirmBtn() throws IOException {
        Button btn = new Button(confirmBtnLocator, "Confirm button");
        btn.clickOnBtn();
    }

    public void clickPromtBoxBtn() throws IOException {
        Button btn = new Button(promtBtnLocator, "Prompt button");
        btn.clickOnBtn();
    }

    public String getAppearedText() throws IOException {
        Alerts alerts = new Alerts(textSentLocator, "Text sent");
        return alerts.getTextFromAlerts();
    }

    public String getAlertText() throws IOException {
        Alerts alerts = new Alerts(null, "Get alert text");
        return alerts.getAlertText();
    }

    public void acceptAlert() throws IOException {
        Alerts alerts = new Alerts(null, "Accept alert");
        alerts.acceptAlert();
    }

    public void typeTextToAlert(String randomText) throws IOException {
        Alerts alerts = new Alerts(null, "Type into alert");
        alerts.typeIntoAlert(randomText);
    }

    public String getConfirmResult() throws IOException {
        Alerts alerts = new Alerts(confirmResultLocator, "Confirm result");
        return alerts.getTextFromAlerts();
    }

}
