package models;

import lombok.Data;

import java.util.List;

@Data
public class BatchModifyMessagesRequest {
    private List<String> ids;
    private List<String> addLabelIds;
    private List<String> removeLabelIds;

    public BatchModifyMessagesRequest() {
    }


    @Override
    public String toString() {
        return "MessageListModify{"
                + "ids=" + ids
                + ", addLabelIds=" + addLabelIds
                + ", removeLabelIds=" + removeLabelIds
                + '}';
    }
}
