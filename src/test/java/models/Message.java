package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Message {
    private String id;
    private String threadId;

    @JsonProperty("labelIds")
    private List<String> labelsIds;
    private String snippet;
    private String historyId;
    private String internalDate;
    private MessagePart payload;
    private int sizeEstimate;
    private String raw;

    @Override
    public String toString() {
        return "Message{"
                + "id='" + id
                + "', threadId='" + threadId
                + "', labelsIds=" + labelsIds
                + ", snippet='" + snippet
                + "', historyId='" + historyId
                + "', internalDate='" + internalDate
                + "', payload=" + payload
                + ", sizeEstimate=" + sizeEstimate
                + ", raw='" + raw
                + "'}";
    }
}
