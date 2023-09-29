package pageobjects;

import base.BaseForm;
import elements.Button;
import org.openqa.selenium.By;

import java.io.IOException;

public class BrowserWindows extends BaseForm {
    private final By newTabBtnLocator = By.id("tabButton");
    private final By elementsBtnOnLeft = By.xpath("//div[text()='Elements']");

    public BrowserWindows() throws IOException {
    }

    public void clickNewTabBtn() throws IOException {
        Button btn = new Button(newTabBtnLocator, "New tab button");
        btn.clickOnBtn();
    }

    public void closeSamplePage() {
        closePage();
    }

    public void clickElementsBtnOnLeft() throws IOException {
        Button btn = new Button(elementsBtnOnLeft, "Elements button");
        btn.clickOnBtn();
    }

    public boolean isBrowserWindowsOpen() {
        return isFormOpen(newTabBtnLocator);
    }
}
