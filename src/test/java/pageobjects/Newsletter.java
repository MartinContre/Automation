package pageobjects;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.TextBox;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.RandomCreator;
import utils.SubscriptionPlan;

import java.util.List;

public class Newsletter extends Form {
    private final ITextBox newsletterSubscriptionPlanContainer = getElementFactory().getTextBox(By.xpath(
            "//form[@id='newsletters-form']"), "Newsletter subscription plan container");
    private final String chooseNewsletterLocator = ".block.w-full.btn-tertiary.unchecked-label.cursor-pointer";
    private List<TextBox> newslettersAvailableList;

    public Newsletter() {
        super(By.xpath("//span[@class='h1 text-secondary']"), "Newsletter");
    }

    /**
     * Checks if the Newsletter form is displayed.
     *
     * @return True if the Newsletter form is displayed, false otherwise.
     */
    public boolean isDisplayed() {
        return state().isDisplayed();
    }

    /**
     * Selects a random subscription plan from the available options.
     *
     * @return The selected SubscriptionPlan.
     */
    public SubscriptionPlan selectRandomSubPlan() {
        if (newslettersAvailableList == null) {
            newslettersAvailableList = getAvailableNewsletterBoxes();
        }

        int selectSubscriptionPlanNum = RandomCreator.createRandomIndexes(1, SubscriptionPlan.values().length).get(0);
        ITextBox chosenSubscriptionPlan = newslettersAvailableList.get(selectSubscriptionPlanNum);

        chosenSubscriptionPlan.findChildElement(By.cssSelector(chooseNewsletterLocator), ElementType.TEXTBOX).click();

        return SubscriptionPlan.values()[selectSubscriptionPlanNum];
    }

    /**
     * Selects the previously selected subscription plan.
     *
     * @param subscriptionPlan The previously selected SubscriptionPlan.
     */
    public void selectPreviousSelectedSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        ITextBox choose = newslettersAvailableList.get(subscriptionPlan.ordinal());
        choose.getJsActions().scrollIntoView();

        choose.findChildElement(By.cssSelector(chooseNewsletterLocator), ElementType.TEXTBOX).click();

        String seePreviewsLocator = ".text-primary.mt-F3.inline-block";
        IButton seePreviewButton = choose.findChildElement(By.cssSelector(seePreviewsLocator), ElementType.BUTTON);
        seePreviewButton.click();
    }

    private List<TextBox> getAvailableNewsletterBoxes() {
        return newsletterSubscriptionPlanContainer.findChildElements(By.cssSelector(
                ".bg-white.shadow-lg"), ElementType.TEXTBOX);
    }
}
