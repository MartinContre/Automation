package elements;

import base.BaseElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.io.IOException;

public class FormField extends BaseElement {
    private static final Logger logger = LogManager.getLogger();
    public FormField(By locator, String name) throws IOException {
        super(locator, name);
    }

    public void fillForm(String data) {
        logger.info("Adding " + getName() + " \"" + data + "\"  to the form");
        findElementByLocator(getLocator()).sendKeys(data);
    }


}
