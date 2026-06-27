package io.github.algonawaz.careermailagent.client.mail;

import io.github.algonawaz.careermailagent.client.oauth.OAuthClient;
import io.github.algonawaz.careermailagent.model.EmailMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import io.github.algonawaz.careermailagent.constants.ApiConstants;
import io.github.algonawaz.careermailagent.dto.gmail.list.GmailMessageListResponse;

import io.github.algonawaz.careermailagent.dto.gmail.detail.GmailMessageResponse;

import io.github.algonawaz.careermailagent.dto.gmail.detail.GmailHeader;
import java.time.Instant;

import java.util.ArrayList;

import io.github.algonawaz.careermailagent.dto.gmail.list.GmailMessageListItem;
import io.github.algonawaz.careermailagent.client.MailClient;

import java.util.List;


@Component
public class GmailMailClient implements MailClient {

    private static final Logger logger =
            LoggerFactory.getLogger(GmailMailClient.class);

    private final RestClient restClient;
    private final OAuthClient oAuthClient;

    public GmailMailClient(RestClient restClient,
                           OAuthClient oAuthClient) {
        this.restClient = restClient;
        this.oAuthClient = oAuthClient;
    }

    @Override
    public List<EmailMessage> getUnreadEmails() {

        logger.info("Reading unread Gmail messages...");
        String accessToken = oAuthClient.getBearerToken();


        GmailMessageListResponse response = restClient.get()
                .uri(ApiConstants.GMAIL_BASE_URL +
                        "?q=" + ApiConstants.UNREAD_QUERY +
                        "&maxResults=" + ApiConstants.DEFAULT_MAX_RESULTS)
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .body(GmailMessageListResponse.class);

        int count = response.getMessages() == null
                ? 0
                : response.getMessages().size();

        logger.info("Found {} unread messages.", count);

        List<EmailMessage> emails = new ArrayList<>();

        if (response.getMessages() != null) {

            for (GmailMessageListItem item : response.getMessages()) {

                GmailMessageResponse gmailMessage =
                        getMessageDetails(item.getId(), accessToken);

                EmailMessage email =
                        toEmailMessage(gmailMessage);

                emails.add(email);
            }
        }

        return emails;
    }

    private GmailMessageResponse getMessageDetails(
            String messageId,
            String accessToken) {

        logger.info("Reading Gmail message {}", messageId);

        return restClient.get()
                .uri(ApiConstants.GMAIL_BASE_URL + "/" + messageId)
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .body(GmailMessageResponse.class);
    }

    private EmailMessage toEmailMessage(GmailMessageResponse response) {

        EmailMessage email = new EmailMessage();

        email.setId(response.getId());
        email.setThreadId(response.getThreadId());
        email.setSnippet(response.getSnippet());

        if (response.getInternalDate() != null) {
            email.setReceivedAt(
                    Instant.ofEpochMilli(
                            Long.parseLong(response.getInternalDate())
                    )
            );
        }

        if (response.getPayload() != null &&
                response.getPayload().getHeaders() != null) {

            for (GmailHeader header : response.getPayload().getHeaders()) {

                if ("Subject".equalsIgnoreCase(header.getName())) {
                    email.setSubject(header.getValue());
                }

                if ("From".equalsIgnoreCase(header.getName())) {
                    email.setFrom(header.getValue());
                }
            }
        }

        email.setUnread(true);

        return email;
    }
}