package io.github.algonawaz.careermailagent.dto.gmail.list;

public class GmailMessageListItem {

    private String id;
    private String threadId;

    public GmailMessageListItem() {
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
}