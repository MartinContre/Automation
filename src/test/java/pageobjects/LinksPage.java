package pageobjects;

import base.BaseForm;
import elements.Button;
import org.openqa.selenium.By;
import utils.ExplicitWait;

import java.io.IOException;

public class LinksPage extends BaseForm {

    private final By homeLinkLocator = By.id("simpleLink");
    private final ExplicitWait wait = new ExplicitWait();

    public LinksPage() throws IOException {
    }

    public void clickHomeLink() throws IOException {
        wait.waitForPageLoad();
        Button btn = new Button(homeLinkLocator, "Home link");
        btn.clickOnBtn();
    }

    public boolean isLinkPageOpen() {
        return isFormOpen(homeLinkLocator);
    }
}
