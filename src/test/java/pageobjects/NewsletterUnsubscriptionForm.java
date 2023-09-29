package pageobjects;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class NewsletterUnsubscriptionForm extends Form {
    private final ITextBox emailAddressInputBox = getElementFactory().getTextBox(
            By.xpath("//input[@id='email']"), "Email address input box");
    private final ITextBox unsubscriptionConfirmationTextBox = getElementFactory().getTextBox(
            By.xpath("//strong[normalize-space()='You are unsubscribed.']"), "Unsubscription Confirmation Text Box");
    private final IButton confirmUnsubscriptionButton = getElementFactory().getButton(
            By.xpath("//button[@type='submit']"), "Confirm Unsubscription Button");

    public NewsletterUnsubscriptionForm() {
        super(By.xpath("//h3[normalize-space()='Newsletter unsubscription']"), "Newsletter unsubscription");
    }


    public boolean isDisplayed() {
        return state().isDisplayed();
    }

    public void fillOutEmailAddressInputBox(String email) {
        emailAddressInputBox.clearAndType(email);
    }

    public void clickConfirmUnsubscriptionButton() {
        confirmUnsubscriptionButton.click();
    }

    public boolean isUnsubscriptionConfirmationTextBoxDisplayed() {
        return unsubscriptionConfirmationTextBox.state().isDisplayed();
    }

}

