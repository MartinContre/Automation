package utils;

import static aquality.selenium.browser.AqualityServices.getBrowser;
import static aquality.selenium.browser.AqualityServices.isBrowserStarted;

public class BrowserUtils {

    public static void goToAndMaximize(String url) {
        getBrowser().goTo(url);
        maximize();
    }

    private static void maximize() {
        getBrowser().maximize();
    }

    /**
     * Close the browser if started.
     */
    public static void closeBrowser() {
        if (isBrowserStarted()) {
            getBrowser().quit();
        }
    }
}
