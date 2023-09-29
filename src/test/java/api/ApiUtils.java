package api;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import models.BatchModifyMessagesRequest;
import models.Message;
import models.MessageList;
import models.ModifyMessageRequest;
import utils.JsonUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The ApiUtils class provides utility methods for making API requests related to messages.
 * It extends the ApiRequests class.
 */
public class ApiUtils extends ApiRequests {
    private static final ISettingsFile CONFIG_DATA_READER = new JsonSettingsFile("config_data.json");

    private static final String EMAIL = CONFIG_DATA_READER.getValue("/mail").toString();

    /**
     * Retrieves all messages for the specified email.
     *
     * @return The list of messages.
     */
    public static MessageList getAllMessages() {
        String endPoint = String.format(END_POINTS_READER.getValue("/get_all_messages").toString(), EMAIL);
        HttpResponse<JsonNode> response = get(endPoint);
        return JsonUtils.deserializeMessages(response.getBody().toString());
    }

    /**
     * Retrieves a message by its ID.
     *
     * @param id The ID of the message to retrieve.
     * @return The retrieved message.
     */
    public static Message getMessageById(String id) {
        String endPoint = String.format(END_POINTS_READER.getValue("/get_message_by_id").toString(), EMAIL, id);
        HttpResponse<JsonNode> response = get(endPoint);
        String responseBody = response.getBody().toString();
        return JsonUtils.deserializeMessage(responseBody);
    }

    /**
     * Marks all messages as read by removing the "UNREAD" label.
     */
    public static void markAllMessagesRead() {
        String endPoint = String.format(END_POINTS_READER.getValue("/post_batch_modify").toString(), EMAIL);
        String labelToRemove = "UNREAD";
        MessageList messageList = getAllMessages();

        if (messageList.getMessages() != null) {
            List<String> messageIds = messageList.getMessages().stream()
                    .map(Message::getId)
                    .collect(Collectors.toList());

            BatchModifyMessagesRequest batchModifyMessagesRequest = new BatchModifyMessagesRequest();
            batchModifyMessagesRequest.setIds(messageIds);
            batchModifyMessagesRequest.setRemoveLabelIds(List.of(labelToRemove));

            post(endPoint, batchModifyMessagesRequest);
        }
    }

    /**
     * Marks a message as read by removing the "UNREAD" label.
     *
     * @param id The ID of the message to mark as read.
     */
    public static void markMessageAsRead(String id) {
        String endPoint = String.format(END_POINTS_READER.getValue("/post_message_modify").toString(), EMAIL, id);
        String labelToRemove = "UNREAD";
        ModifyMessageRequest modifyMessageRequest = new ModifyMessageRequest();
        modifyMessageRequest.setRemoveLabelIds(List.of(labelToRemove));
        post(endPoint, modifyMessageRequest);
    }

    /**
     * Extracts the ID of the latest unread message.
     *
     * @return The ID of the latest unread message.
     */
    public static String extractLatestUnreadMessageId() {
        MessageList messageList = getAllMessages();
        while (messageList.getMessages() == null) {
            messageList = getAllMessages();
        }
        return messageList.getMessages().get(0).getId();
    }

    /**
     * Checks if the email is from Euronews.
     *
     * @return True if the email is from Euronews, false otherwise.
     */
    public static boolean isEuronewsEmail() {
        if (getAllMessages() == null) {
            List<Message> cancellationMessages = getAllMessages().getMessages().stream()
                    .filter(msg -> msg.getPayload().getHeaders().stream()
                            .anyMatch(header -> header.getName().equals("Subject") && header.getValue().contains("canceling your subscription")))
                    .toList();
            return cancellationMessages.isEmpty();
        } else {
            return true;
        }
    }

    /**
     * Checks if there are no new messages.
     *
     * @return True if there are no new messages, false otherwise.
     */
    public static boolean noNewMessages() {
        MessageList messageList = getAllMessages();
        if (messageList == null || messageList.getMessages() == null) {
            return true;
        } else {
            return messageList.getMessages().stream()
                    .map(Message::getPayload)
                    .flatMap(payload -> payload.getHeaders().stream())
                    .filter(header -> header.getName().equalsIgnoreCase("Subject"))
                    .noneMatch(header -> header.getValue().contains("canceling your subscription"));
        }
    }
}
