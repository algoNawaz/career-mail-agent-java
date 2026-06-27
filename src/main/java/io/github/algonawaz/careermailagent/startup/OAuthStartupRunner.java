package io.github.algonawaz.careermailagent.startup;

import io.github.algonawaz.careermailagent.client.mail.GmailMailClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import io.github.algonawaz.careermailagent.model.EmailMessage;
import java.util.List;

@Component
public class OAuthStartupRunner implements CommandLineRunner {

    private static final Logger logger =
            LoggerFactory.getLogger(OAuthStartupRunner.class);

    private final GmailMailClient gmailMailClient;

    public OAuthStartupRunner(GmailMailClient gmailMailClient) {
        this.gmailMailClient = gmailMailClient;
    }

    @Override
    public void run(String... args) {

        logger.info("Testing Gmail integration...");

        List<EmailMessage> emails = gmailMailClient.getUnreadEmails();

        logger.info("Total Emails Received: {}", emails.size());

        for (EmailMessage email : emails) {

            logger.info("----------------------------------------");
            logger.info("From: {}", email.getFrom());
            logger.info("Subject: {}", email.getSubject());
            logger.info("Snippet: {}", email.getSnippet());
            logger.info("Received: {}", email.getReceivedAt());
        }
    }
}