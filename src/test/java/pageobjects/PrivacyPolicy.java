package pageobjects;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PrivacyPolicy extends Form {
    private final IButton continueWithoutAgreeingBtn = getElementFactory().getButton(By.xpath(
            "//span[@role='button']"), "Continue Without Agreeing Button");

    public PrivacyPolicy() {
        super(By.xpath("//span[@class='didomi-notice-data-processing-title']"), "Privacy policy");
    }

    public boolean isPrivacyPolicyDisplayed() {
        return state().isDisplayed();
    }

    public void clickContinueWithoutAgreeingBtn() {
        continueWithoutAgreeingBtn.click();
    }
}
