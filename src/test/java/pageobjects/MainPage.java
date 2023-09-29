package pageobjects;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {
    private final ILink newslettersLink = getElementFactory().getLink(By.xpath(
            "//span[@class='u-margin-start-1'][normalize-space()='Newsletters']"),
            "Newsletters link");

    public MainPage() {
        super(By.xpath("//a[@aria-label='Euronews Logo']//h1//*[name()='svg']"), "Main page");
    }

    public boolean isDisplayed() {
        return state().isDisplayed();
    }

    public void clickNewslettersBtn() {
        newslettersLink.click();
    }
}
