package io.github.algonawaz.careermailagent.constants;

public final class ApiConstants {

    private ApiConstants() {
    }

    // OAuth Endpoint
    public static final String GOOGLE_OAUTH_TOKEN_URL =
            "https://oauth2.googleapis.com/token";

    // OAuth Parameters
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_SECRET = "client_secret";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String GRANT_TYPE = "grant_type";

    public static final String GRANT_TYPE_REFRESH_TOKEN =
            "refresh_token";

    // Gmail API
    public static final String GMAIL_BASE_URL =
            "/users/me/messages";

    public static final String GMAIL_LABELS =
            "/users/me/labels";

    public static final String MODIFY_MESSAGE =
            "/users/me/messages/%s/modify";


    public static final String UNREAD_QUERY =
            "is:unread -label:CareerMailAgent-Processed";

    public static final Integer DEFAULT_MAX_RESULTS = 20;




}