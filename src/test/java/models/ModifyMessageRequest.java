package models;

import lombok.Data;

import java.util.List;

@Data
public class ModifyMessageRequest {
    private List<String> addLabelIds;
    private List<String> removeLabelIds;

    public ModifyMessageRequest() {

    }
}
