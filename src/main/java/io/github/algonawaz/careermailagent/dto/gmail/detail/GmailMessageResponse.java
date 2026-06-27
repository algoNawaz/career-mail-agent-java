package io.github.algonawaz.careermailagent.dto.gmail.detail;

public class GmailMessageResponse {

    private String id;

    private String threadId;

    private String snippet;

    private String internalDate;

    private GmailPayload payload;

    public GmailMessageResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getInternalDate() {
        return internalDate;
    }

    public void setInternalDate(String internalDate) {
        this.internalDate = internalDate;
    }

    public GmailPayload getPayload() {
        return payload;
    }

    public void setPayload(GmailPayload payload) {
        this.payload = payload;
    }
}