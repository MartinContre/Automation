package browser;

public class BrowserFactory {
    public static DriverManager getManager(String browserName) {
        return switch (browserName.toLowerCase()) {
            case "firefox" -> new FirefoxCaps();
            case "chrome" -> new ChromeCaps();
            default -> throw new IllegalArgumentException("Browser not support");
        };
    }
}
