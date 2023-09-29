package models;

import lombok.Data;

@Data
public class MessagePartBody {
    private String attachmentId;
    private int size;
    private String data;


    @Override
    public String toString() {
        return "MessagePartBody{"
                + "attachmentId='" + attachmentId
                + "', size=" + size
                + ", data='" + data
                + "'}";
    }
}
