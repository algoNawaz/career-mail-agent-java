package io.github.algonawaz.careermailagent.client.label;

import io.github.algonawaz.careermailagent.client.oauth.OAuthClient;
import io.github.algonawaz.careermailagent.constants.ApiConstants;
import io.github.algonawaz.careermailagent.constants.CareerConstants;
import io.github.algonawaz.careermailagent.dto.gmail.label.CreateLabelRequest;
import io.github.algonawaz.careermailagent.dto.gmail.label.GmailLabel;
import io.github.algonawaz.careermailagent.dto.gmail.label.GmailLabelListResponse;
import io.github.algonawaz.careermailagent.dto.gmail.label.ModifyMessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;


/**
 * Manages Gmail labels, including creating the processed label
 * and marking emails as processed.
 */
@Component
public class GmailLabelClient {

    private static final Logger logger =
            LoggerFactory.getLogger(GmailLabelClient.class);

    private final RestClient restClient;
    private final OAuthClient oAuthClient;

    public GmailLabelClient(
            @Qualifier("gmailRestClient") RestClient restClient,
            OAuthClient oAuthClient) {

        this.restClient = restClient;
        this.oAuthClient = oAuthClient;
    }

    public List<GmailLabel> getLabels() {

        logger.info("Reading Gmail labels...");

        String token = oAuthClient.getBearerToken();

        GmailLabelListResponse response =
                restClient.get()
                        .uri(ApiConstants.GMAIL_LABELS)
                        .header(
                                "Authorization",
                                "Bearer " + token
                        )
                        .retrieve()
                        .body(GmailLabelListResponse.class);

        return response.getLabels();
    }

    private GmailLabel findLabelByName(
            String labelName) {

        List<GmailLabel> labels = getLabels();

        for (GmailLabel label : labels) {

            if (labelName.equals(label.getName())) {
                return label;
            }

        }

        return null;
    }


    private GmailLabel createLabel() {

        logger.info("Creating processed label...");

        CreateLabelRequest request =
                new CreateLabelRequest();

        request.setName(CareerConstants.PROCESSED_LABEL);

        request.setLabelListVisibility("labelShow");

        request.setMessageListVisibility("show");

        String token = oAuthClient.getBearerToken();

        return restClient.post()
                .uri(ApiConstants.GMAIL_LABELS)
                .header(
                        "Authorization",
                        "Bearer " + token
                )
                .body(request)
                .retrieve()
                .body(GmailLabel.class);
    }

    public GmailLabel ensureProcessedLabelExists() {

        GmailLabel label =
                findLabelByName(
                        CareerConstants.PROCESSED_LABEL);

        if (label != null) {

            logger.info(
                    "Processed label already exists.");

            return label;
        }

        logger.info(
                "Processed label not found. Creating...");

        return createLabel();
    }

    public void addLabelToMessage(
            String messageId,
            String labelId) {

        // Step 1
        ModifyMessageRequest request =
                new ModifyMessageRequest();

        // Step 2
        request.setAddLabelIds(
                List.of(labelId));

        // Step 3 ← ADD IT HERE
        String token =
                oAuthClient.getBearerToken();

        // Step 4
        restClient.post()
                .uri(
                        ApiConstants.MODIFY_MESSAGE
                                .formatted(messageId)
                )
                .header(
                        "Authorization",
                        "Bearer " + token
                )
                .body(request)
                .retrieve()
                .toBodilessEntity();

        // Step 5
        logger.info(
                "Added processed label to message {}",
                messageId
        );
    }




}