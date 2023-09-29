package test;

import api.ApiUtils;
import models.Message;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.MainPage;
import pageobjects.Newsletter;
import pageobjects.NewsletterUnsubscriptionForm;
import pageobjects.PrivacyPolicy;
import pageobjects.SubscriptionPlanForm;
import pageobjects.SubscriptionPreviewForm;
import pageobjects.SuccessfullySubscriptionConfirmationForm;
import utils.StringUtils;
import utils.SubscriptionPlan;

import java.util.List;

public class GmailApiTest extends BaseTest {
    private static final String EMAIL_STRING = CONFIG_DATA_READER.getValue("/mail").toString();

    @Test
    void test() {
        MainPage mainPage = new MainPage();
        PrivacyPolicy privacyPolicy = new PrivacyPolicy();
        Newsletter newsletter = new Newsletter();
        SubscriptionPlanForm subscriptionPlanForm = new SubscriptionPlanForm();
        SuccessfullySubscriptionConfirmationForm successfullySubscriptionConfirmationForm = new SuccessfullySubscriptionConfirmationForm();
        SubscriptionPreviewForm subscriptionPreviewForm = new SubscriptionPreviewForm();
        NewsletterUnsubscriptionForm newsletterUnsubscriptionForm = new NewsletterUnsubscriptionForm();

        Assert.assertTrue(privacyPolicy.isPrivacyPolicyDisplayed(), "Privacy Policy has not been displayed!");
        privacyPolicy.clickContinueWithoutAgreeingBtn();
        Assert.assertTrue(mainPage.isDisplayed(), "Main page has not been open!");
        mainPage.clickNewslettersBtn();
        Assert.assertTrue(newsletter.isDisplayed(), "Newsletter Menu has not been open!");
        SubscriptionPlan selected = newsletter.selectRandomSubPlan();
        Assert.assertTrue(subscriptionPlanForm.isDisplayed(), "Subscription Plan Email Form has not been displayed!");
        subscriptionPlanForm.fillOutEmailTxtBox(EMAIL_STRING);
        subscriptionPlanForm.clickSubmitSubscriptionFormBtn();

        String latestMessageId = ApiUtils.extractLatestUnreadMessageId();
        Message message = ApiUtils.getMessageById(latestMessageId);
        Assert.assertTrue(ApiUtils.isEuronewsEmail(),
                "There is no message received to confirm subscription in Euronews platform!");
        ApiUtils.markMessageAsRead(latestMessageId);
        List<String> redirectUrl = StringUtils.extractRedirectUrls(message);

        getBrowser().goTo(redirectUrl.get(0));
        Assert.assertTrue(successfullySubscriptionConfirmationForm.isDisplayed());
        successfullySubscriptionConfirmationForm.clickBackToSiteBtn();

        Assert.assertTrue(mainPage.isDisplayed(), "Main page has not been open!");
        mainPage.clickNewslettersBtn();
        newsletter.selectPreviousSelectedSubscriptionPlan(selected);
        Assert.assertTrue(subscriptionPreviewForm.isDisplayed(), "Subscription Preview Form has not been displayed!");
        subscriptionPreviewForm.clickUnsubscribeBtn();
        Assert.assertTrue(newsletterUnsubscriptionForm.isDisplayed(),
                "Newsletter Unsubscription Form has not been displayed!");
        newsletterUnsubscriptionForm.fillOutEmailAddressInputBox(EMAIL_STRING);
        newsletterUnsubscriptionForm.clickConfirmUnsubscriptionButton();

        Assert.assertTrue(newsletterUnsubscriptionForm.isUnsubscriptionConfirmationTextBoxDisplayed(),
                "Unsubscription failed, confirmation has not been displayed!");


        Assert.assertTrue(ApiUtils.noNewMessages(), "Received a cancellation message");
    }
}
