package io.github.algonawaz.careermailagent.dto.gmail.label;

import java.util.List;

public class ModifyMessageRequest {

    private List<String> addLabelIds;

    public List<String> getAddLabelIds() {
        return addLabelIds;
    }

    public void setAddLabelIds(List<String> addLabelIds) {
        this.addLabelIds = addLabelIds;
    }
}