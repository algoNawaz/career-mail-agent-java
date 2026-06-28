package io.github.algonawaz.careermailagent.service.notification;


import io.github.algonawaz.careermailagent.dto.telegram.TelegramMessageRequest;
import io.github.algonawaz.careermailagent.model.EmailMessage;
import io.github.algonawaz.careermailagent.properties.TelegramProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sends career email notifications to Telegram
 * using the Telegram Bot API.
 */
@Service
public class TelegramNotificationService
        implements NotificationService {

    private static final Logger logger =
            LoggerFactory.getLogger(TelegramNotificationService.class);

    private final RestClient restClient;

    private final TelegramProperties telegramProperties;

    public TelegramNotificationService(
            @Qualifier("telegramRestClient") RestClient restClient,
            TelegramProperties telegramProperties) {

        this.restClient = restClient;
        this.telegramProperties = telegramProperties;
    }

    @Override
    public void sendCareerNotification(
            EmailMessage email) {

        String message =
                """
                🚨 Career Email Detected
                
                From: %s
                
                Subject: %s
                """
                        .formatted(
                                email.getFrom(),
                                email.getSubject()
                        );

        logger.info("Preparing Telegram notification...");

        String url =
                "/bot"
                        + telegramProperties.getBotToken()
                        + "/sendMessage";

        TelegramMessageRequest request =
                new TelegramMessageRequest();

        request.setChat_id(
                telegramProperties.getChatId());

        request.setText(message);

        restClient.post()
                .uri(url)
                .body(request)
                .retrieve()
                .toBodilessEntity();
        logger.info("Telegram notification sent successfully.");
    }
}