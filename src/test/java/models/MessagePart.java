package models;

import lombok.Data;

import java.util.List;

@Data
public class MessagePart {
    private String partId;
    private String mimeType;
    private String fileName;
    private List<Header> headers;
    private MessagePartBody body;
    private List<MessagePart> parts;

    @Override
    public String toString() {
        return "MessagePart{"
                + "partId='" + partId + "'"
                + ", mimeType='" + mimeType
                + "', filename='" + fileName
                + "', headers=" + headers
                + ", body=" + body
                + ", parts=" + parts
                + "}";
    }
}
