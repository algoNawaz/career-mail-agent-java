package io.github.algonawaz.careermailagent.dto.gmail.detail;

import java.util.List;

public class GmailPayload {

    private List<GmailHeader> headers;

    public GmailPayload() {
    }

    public List<GmailHeader> getHeaders() {
        return headers;
    }

    public void setHeaders(List<GmailHeader> headers) {
        this.headers = headers;
    }
}