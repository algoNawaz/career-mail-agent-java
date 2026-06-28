package io.github.algonawaz.careermailagent.client.oauth;

import io.github.algonawaz.careermailagent.dto.OAuthTokenResponse;
import io.github.algonawaz.careermailagent.properties.GmailProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import io.github.algonawaz.careermailagent.constants.ApiConstants;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Handles OAuth2 authentication with Google and retrieves
 * access tokens using the configured refresh token.
 */
@Component
public class OAuthClient {

    private static final Logger logger =
            LoggerFactory.getLogger(OAuthClient.class);


    private final RestClient restClient;
    private final GmailProperties gmailProperties;

    public OAuthClient(@Qualifier("gmailRestClient") RestClient restClient,
                       GmailProperties gmailProperties) {

        this.restClient = restClient;
        this.gmailProperties = gmailProperties;
    }

    public OAuthTokenResponse getAccessToken() {

        logger.info("Client ID: {}", gmailProperties.getClientId());
        logger.info("Client Secret loaded: {}", gmailProperties.getClientSecret() != null);
        logger.info("Refresh Token loaded: {}", gmailProperties.getRefreshToken() != null);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

        formData.add(ApiConstants.CLIENT_ID,
                gmailProperties.getClientId());

        formData.add(ApiConstants.CLIENT_SECRET,
                gmailProperties.getClientSecret());

        formData.add(ApiConstants.REFRESH_TOKEN,
                gmailProperties.getRefreshToken());

        formData.add(ApiConstants.GRANT_TYPE,
                ApiConstants.GRANT_TYPE_REFRESH_TOKEN);

        logger.info("Sending OAuth request to Google...");

        try {

            OAuthTokenResponse response = restClient.post()
                    .uri(ApiConstants.GOOGLE_OAUTH_TOKEN_URL)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(formData)
                    .retrieve()
                    .body(OAuthTokenResponse.class);

            logger.info("Access token received successfully.");

            return response;

        } catch (Exception ex) {

            logger.error("Failed to get OAuth access token.", ex);

            throw new IllegalStateException(
                    "Unable to obtain Gmail access token",
                    ex
            );
        }
    }

    public String getBearerToken() {

        return getAccessToken().getAccessToken();
    }

}