package io.github.algonawaz.careermailagent.dto.gmail.label;

import java.util.List;

public class GmailLabelListResponse {

    private List<GmailLabel> labels;

    public List<GmailLabel> getLabels() {
        return labels;
    }

    public void setLabels(List<GmailLabel> labels) {
        this.labels = labels;
    }
}