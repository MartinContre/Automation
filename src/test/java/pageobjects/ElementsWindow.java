package pageobjects;

import base.BaseForm;
import elements.Button;
import org.openqa.selenium.By;

import java.io.IOException;

public class ElementsWindow extends BaseForm {
    private final By webTablesBtnLocator = By.xpath("//span[text()='Web Tables']");
    private final By linksBtnLocator = By.xpath("//span[text()='Links']");

    public ElementsWindow() throws IOException {
    }

    public void clickLinksBtn() throws IOException {
        Button btn = new Button(linksBtnLocator, "Links button");
        btn.clickOnBtn();
    }

    public void clickWebTablesBtn() throws IOException {
        Button btn = new Button(webTablesBtnLocator, "Web tables button");
        btn.clickOnBtn();
    }
}
