package models;

import lombok.Data;

import java.util.List;

@Data
public class MessageList {
    private List<Message> messages;
    private String nextPageToken;
    private int resultSizeEstimate;

    @Override
    public String toString() {
        return "MessageList{"
                + "messages=" + messages
                + ", nextPageToken='" + nextPageToken
                + "', resultSizeEstimate=" + resultSizeEstimate
                + "}";
    }
}
