package pageobjects;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class SuccessfullySubscriptionConfirmationForm extends Form {
    private final IButton backToTheSiteBtn = getElementFactory().getButton(By.xpath(
            "//a[@aria-label='Back to the site']"), "Back to the site button");

    public SuccessfullySubscriptionConfirmationForm() {
        super(By.xpath("//div[@class='enw-block-confirmation__text']"),
                "Successfully subscription confirmation");
    }

    public boolean isDisplayed() {
        return state().isDisplayed();
    }

    public void clickBackToSiteBtn() {
        backToTheSiteBtn.click();
    }

}
