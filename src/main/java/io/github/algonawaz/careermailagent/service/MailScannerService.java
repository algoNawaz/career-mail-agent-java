package io.github.algonawaz.careermailagent.service;

import io.github.algonawaz.careermailagent.client.MailClient;
import io.github.algonawaz.careermailagent.client.label.GmailLabelClient;
import io.github.algonawaz.careermailagent.dto.gmail.label.GmailLabel;
import io.github.algonawaz.careermailagent.model.EmailMessage;
import io.github.algonawaz.careermailagent.service.detector.CareerDetector;
import io.github.algonawaz.careermailagent.service.notification.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Scans unread Gmail messages, detects career-related emails,
 * sends Telegram notifications, and marks emails as processed.
 */
@Service
public class MailScannerService {

    private static final Logger logger =
            LoggerFactory.getLogger(MailScannerService.class);

    private final MailClient mailClient;
    private final CareerDetector careerDetector;
    private final NotificationService notificationService;
    private final GmailLabelClient gmailLabelClient;

    public MailScannerService(
            MailClient mailClient,
            CareerDetector careerDetector,
            NotificationService notificationService,
            GmailLabelClient gmailLabelClient) {

        this.mailClient = mailClient;
        this.careerDetector = careerDetector;
        this.notificationService = notificationService;
        this.gmailLabelClient = gmailLabelClient;
    }

    public List<EmailMessage> scanCareerEmails() {

        logger.info("========================================");
        logger.info("Career Mail Agent Scan Started");
        logger.info("========================================");
        logger.info("Scanning inbox...");

        List<EmailMessage> emails =
                mailClient.getUnreadEmails();

        logger.info(
                "Unread emails found: {}",
                emails.size()
        );

        List<EmailMessage> careerEmails =
                emails.stream()
                        .filter(careerDetector::isCareerEmail)
                        .toList();

        logger.info(
                "Career emails found: {}",
                careerEmails.size()
        );
        GmailLabel processedLabel =
                gmailLabelClient.ensureProcessedLabelExists();

        for (EmailMessage email : careerEmails) {

            try {

                notificationService.sendCareerNotification(email);

                gmailLabelClient.addLabelToMessage(
                        email.getId(),
                        processedLabel.getId()
                );

                logger.info(
                        "✓ Processed: {}",
                        email.getSubject()
                );

            } catch (Exception ex) {

                logger.error(
                        "✗ Failed: {}",
                        email.getSubject(),
                        ex
                );

            }
        }

        logger.info("Scan completed.");
        logger.info("========================================");
        return careerEmails;
    }

}