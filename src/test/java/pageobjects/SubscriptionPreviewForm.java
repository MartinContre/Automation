package pageobjects;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.TextBox;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class SubscriptionPreviewForm extends Form {
    private final ITextBox unsubscribeButton = getElementFactory().getTextBox(
            By.xpath("//a[contains(@href, 'https://services.ownpage.fr/unsubscribe/')]"), "Unsubscribe button"
    );
    private final ITextBox previewForm = getElementFactory().getTextBox(By.cssSelector(".jquery-modal.blocker.current"),
            "Preview Form");
    private static final String iframeLocator = "iframe-preview";

    public SubscriptionPreviewForm() {
        super(By.cssSelector(".jquery-modal.blocker.current"), "Preview form");
    }

    public boolean isDisplayed() {
        return state().isDisplayed();
    }

    /**
     * Clicks the unsubscribe button on the subscription preview form.
     * Switches to the iframe containing the button before clicking it.
     */
    public void clickUnsubscribeBtn() {
        TextBox iframe = previewForm.findChildElement(By.className(iframeLocator), ElementType.TEXTBOX);
        AqualityServices.getBrowser().getDriver().switchTo().frame(iframe.getElement());
        unsubscribeButton.getJsActions().scrollIntoView();
        AqualityServices.getBrowser().goTo(unsubscribeButton.getAttribute("href"));
    }
}
