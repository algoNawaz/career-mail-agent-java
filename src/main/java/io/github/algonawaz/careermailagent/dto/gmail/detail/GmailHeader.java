package io.github.algonawaz.careermailagent.dto.gmail.detail;

public class GmailHeader {

    private String name;
    private String value;

    public GmailHeader() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}