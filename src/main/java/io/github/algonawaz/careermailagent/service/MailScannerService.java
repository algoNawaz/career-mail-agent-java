package io.github.algonawaz.careermailagent.service;

import io.github.algonawaz.careermailagent.client.MailClient;
import io.github.algonawaz.careermailagent.model.EmailMessage;
import io.github.algonawaz.careermailagent.service.detector.CareerDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailScannerService {

    private static final Logger logger =
            LoggerFactory.getLogger(MailScannerService.class);

    private final MailClient mailClient;
    private final CareerDetector careerDetector;

    public MailScannerService(
            MailClient mailClient,
            CareerDetector careerDetector) {

        this.mailClient = mailClient;
        this.careerDetector = careerDetector;
    }

    public List<EmailMessage> scanCareerEmails() {

        logger.info("Scanning inbox...");

        List<EmailMessage> emails =
                mailClient.getUnreadEmails();

        List<EmailMessage> careerEmails =
                emails.stream()
                        .filter(careerDetector::isCareerEmail)
                        .toList();

        logger.info(
                "Career emails found: {}",
                careerEmails.size()
        );

        return careerEmails;
    }

}