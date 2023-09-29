package pageobjects;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class SubscriptionPlanForm extends Form {
    private final ITextBox emailTextBox = getElementFactory().getTextBox(By.xpath(
            "//input[@placeholder='Enter your email']"), "Email text box");
    private final IButton submitFormBtn = getElementFactory().getButton(By.xpath(
            "//input[contains(@class,'btn-primary') and @value='Submit']"), "Submit subscription form");

    public SubscriptionPlanForm() {
        super(By.id("register-newsletters-form"), "Register subscription plan email form");
    }

    public boolean isDisplayed() {
        return state().isDisplayed();
    }

    public void fillOutEmailTxtBox(String email) {
        emailTextBox.sendKeys(email);
    }

    public void clickSubmitSubscriptionFormBtn() {
        submitFormBtn.clickAndWait();
    }
}
