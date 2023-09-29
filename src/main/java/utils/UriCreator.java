package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import org.apache.hc.core5.net.URIBuilder;

public class UriCreator {
    private static final ISettingsFile CONFIG_DATA = SettingsFilesUtils.getConfigDataFile();
    private static final String HTTP = "http";
    private static final String DOMAIN = CONFIG_DATA.getValue("/domain").toString();

    /**
     * Generate the URL using schema "http" and the domain provided.
     *
     * @return the generated url.
     */
    public static String getUri() {
        URIBuilder uriBuilder = new URIBuilder()
                .setScheme(HTTP)
                .setHost(DOMAIN);
        return uriBuilder.toString();
    }

}
