package models;

public class ConfigModel {
    private String browser;
    private String testURL;
    private String pathDataTest;
    private int timeWait;

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getTestURL() {
        return testURL;
    }

    public void setTestURL(String testURL) {
        this.testURL = testURL;
    }

    public String getPathDataTest() {
        return pathDataTest;
    }

    public void setPathDataTest(String pathDataTest) {
        this.pathDataTest = pathDataTest;
    }

    public int getTimeWait() {
        return timeWait;
    }

    public void setTimeWait(int timeWait) {
        this.timeWait = timeWait;
    }
}
