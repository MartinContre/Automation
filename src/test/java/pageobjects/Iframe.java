package pageobjects;

import base.BaseForm;
import elements.IframeElements;
import org.openqa.selenium.By;

import java.io.IOException;

public class Iframe extends BaseForm {

    private final By framesWrapperLocator = By.id("framesWrapper");
    private final By bodyLocator = By.tagName("body");
    private final By childIframeLocator = By.xpath("/html/body/iframe");

    public Iframe() throws IOException {
    }

    public boolean isNestedFramesDisplayed() {
        return isFormOpen(framesWrapperLocator);
    }

    public String getParentFrameTxt() throws IOException {
        IframeElements iframeElements = new IframeElements(bodyLocator, "Parent frame text");
        return iframeElements.getParentFrameTxt();
    }

    public String getChildrenIframeTxt() throws IOException {
        IframeElements iframeElements = new IframeElements(bodyLocator, "Children frame text");
        return iframeElements.getChildIframeTxt(childIframeLocator);
    }


}
