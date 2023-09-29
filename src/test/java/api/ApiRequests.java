package api;

import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import utils.JsonUtils;

/**
 * The ApiRequests class provides methods for making HTTP requests to an API.
 */
public class ApiRequests {
    private static final ISettingsFile CREDENTIALS_READER = new JsonSettingsFile("credentials.json");
    protected static final ISettingsFile END_POINTS_READER = new JsonSettingsFile("end_point.json");
    private static final String BASE_URI = END_POINTS_READER.getValue("/base_URI").toString();
    private static final String ACCESS_TOKEN = CREDENTIALS_READER.getValue("/access_token").toString();

    /**
     * Sends a GET request to the specified endpoint.
     *
     * @param endPoint The endpoint to send the GET request to.
     * @return The HTTP response as a JsonNode object.
     * @throws RuntimeException If an error occurs while sending the request.
     */
    public static HttpResponse<JsonNode> get(String endPoint) {
        String url = BASE_URI + endPoint;
        try {
            Logger.getInstance().info("Retrieving HTTP response from: " + url);
            return Unirest.get(url)
                    .header("Authorization", "Bearer " + ACCESS_TOKEN)
                    .header("Content-Type", "application/json")
                    .asJson();
        } catch (UnirestException e) {
            String errorMessage = "Failed to retrieve HTTP response from: " + url + ". Error message: " + e.getMessage();
            Logger.getInstance().error(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    /**
     * Sends a POST request to the specified endpoint with the provided object as the request body.
     *
     * @param endPoint The endpoint to send the POST request to.
     * @param object   The object to be sent as the request body.
     * @throws RuntimeException If an error occurs while sending the request.
     */
    public static void post(String endPoint, Object object) {
        String requestBody = JsonUtils.serializeObjectToString(object);
        String requestUrl = BASE_URI + endPoint;
        try {
            Logger.getInstance().info("Sending POST request to: " + requestUrl);
            Unirest.post(requestUrl)
                    .header("Authorization", "Bearer " + ACCESS_TOKEN)
                    .header("Content-Type", "application/json")
                    .body(requestBody)
                    .asJson();
        } catch (UnirestException e) {
            String errorMessage = String.format("Failed to retrieve HTTP POST response from: %s. Error message: %s", requestUrl, e.getMessage());
            Logger.getInstance().error(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }
}
