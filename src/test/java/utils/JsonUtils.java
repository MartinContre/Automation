package utils;

import aquality.selenium.core.logging.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import models.Message;
import models.MessageList;

public class JsonUtils {
    private static final Gson gson = new Gson();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Deserialize the response body into a MessageList object.
     *
     * @param responseBody The response body to deserialize.
     * @return The deserialized MessageList object.
     */
    public static MessageList deserializeMessages(String responseBody) {
        try {
            Logger.getInstance().info("Deserializing messages from response.");
            return objectMapper.readValue(responseBody, MessageList.class);
        } catch (JsonProcessingException e) {
            Logger.getInstance().error("Failed to deserialize response body. " + e);
            return null;
        }
    }

    /**
     * Deserialize the response body into a Message object.
     *
     * @param responseBody The response body to deserialize.
     * @return The deserialized Message object.
     */
    public static Message deserializeMessage(String responseBody) {
        Logger.getInstance().info("Deserializing message from response.");
        return gson.fromJson(responseBody, Message.class);
    }

    /**
     * Serialize an object to its JSON string representation.
     *
     * @param object The object to serialize.
     * @return The JSON string representation of the object.
     */
    public static String serializeObjectToString(Object object) {
        try {
            Logger.getInstance().info("Serializing object: " + object);
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            Logger.getInstance().error("Failed to serialize object." + e);
            return null;
        }
    }
}
