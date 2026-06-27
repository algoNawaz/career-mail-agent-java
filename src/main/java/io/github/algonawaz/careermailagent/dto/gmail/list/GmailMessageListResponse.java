package io.github.algonawaz.careermailagent.dto.gmail.list;

import java.util.List;

public class GmailMessageListResponse {

    private List<GmailMessageListItem> messages;

    private Integer resultSizeEstimate;

    public GmailMessageListResponse() {
    }

    public List<GmailMessageListItem> getMessages() {
        return messages;
    }

    public void setMessages(List<GmailMessageListItem> messages) {
        this.messages = messages;
    }

    public Integer getResultSizeEstimate() {
        return resultSizeEstimate;
    }

    public void setResultSizeEstimate(Integer resultSizeEstimate) {
        this.resultSizeEstimate = resultSizeEstimate;
    }
}