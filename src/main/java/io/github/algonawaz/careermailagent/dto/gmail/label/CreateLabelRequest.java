package io.github.algonawaz.careermailagent.dto.gmail.label;

public class CreateLabelRequest {

    private String name;

    private String labelListVisibility;

    private String messageListVisibility;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabelListVisibility() {
        return labelListVisibility;
    }

    public void setLabelListVisibility(String labelListVisibility) {
        this.labelListVisibility = labelListVisibility;
    }

    public String getMessageListVisibility() {
        return messageListVisibility;
    }

    public void setMessageListVisibility(String messageListVisibility) {
        this.messageListVisibility = messageListVisibility;
    }
}