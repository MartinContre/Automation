package pageobjects;

import base.BaseForm;
import elements.FramesElements;
import org.openqa.selenium.By;

import java.io.IOException;

public class Frames extends BaseForm {
    private final By frame1Locator = By.id("frame1");
    private final By frame2Locator = By.id("frame2");
    private final By sampleHeadingLocator = By.id("sampleHeading");

    public Frames() throws IOException {
    }

    public boolean isFramesDisplayed() {
        return isFormOpen(frame2Locator);
    }

    public String getTextFrame1() throws IOException {
        FramesElements frameElements = new FramesElements(frame1Locator, "Frame 1");
        return frameElements.getTextFromFrame(sampleHeadingLocator);
    }

    public String getTextFrame2() throws IOException {
        FramesElements frameElements = new FramesElements(frame2Locator, "Frame 2");
        return frameElements.getTextFromFrame(sampleHeadingLocator);
    }
}
