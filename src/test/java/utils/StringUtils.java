package utils;

import models.Message;
import models.MessagePart;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class StringUtils {
    private static final String REDIR_URL = "https://redir.";

    /**
     * Extracts redirect URLs from a given message.
     *
     * @param message The message from which to extract redirect URLs.
     * @return A list of redirect URLs.
     */
    public static List<String> extractRedirectUrls(Message message) {
        List<String> redirectUrls = new ArrayList<>();
        if (message != null && message.getPayload() != null && message.getPayload().getParts() != null) {
            for (MessagePart part : message.getPayload().getParts()) {
                if (part.getBody() != null && part.getBody().getData() != null) {
                    String decodedData = decodeData(part.getBody().getData());
                    redirectUrls.addAll(extractUrlsContainingSubstring(decodedData));
                }
            }
        }
        return redirectUrls;
    }

    /**
     * Decodes Base64-encoded data.
     *
     * @param data The Base64-encoded data to decode.
     * @return The decoded data as a string.
     */
    private static String decodeData(String data) {
        return new String(Base64.getUrlDecoder().decode(data));
    }

    /**
     * Extracts URLs containing a specific substring from a given text.
     *
     * @param text The text from which to extract URLs.
     * @return A list of URLs containing the specified substring.
     */
    private static List<String> extractUrlsContainingSubstring(String text) {
        List<String> urls = new ArrayList<>();
        int index = text.indexOf(StringUtils.REDIR_URL);
        while (index >= 0) {
            int endIndex = text.indexOf("\"", index);
            if (endIndex >= 0) {
                urls.add(text.substring(index, endIndex));
                index = text.indexOf(StringUtils.REDIR_URL, endIndex);
            } else {
                break;
            }
        }
        return urls;
    }
}
