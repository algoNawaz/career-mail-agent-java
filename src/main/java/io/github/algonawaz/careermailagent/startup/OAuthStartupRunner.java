package io.github.algonawaz.careermailagent.startup;

import io.github.algonawaz.careermailagent.service.MailScannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class OAuthStartupRunner implements CommandLineRunner {

    private static final Logger logger =
            LoggerFactory.getLogger(OAuthStartupRunner.class);

    private final MailScannerService mailScannerService;

    public OAuthStartupRunner(
            MailScannerService mailScannerService) {

        this.mailScannerService = mailScannerService;
    }

    @Override
    public void run(String... args) {

        logger.info("========================================");
        logger.info("Career Mail Agent Started");
        logger.info("========================================");

        try {

            mailScannerService.scanCareerEmails();

            logger.info("Career Mail Agent completed successfully.");

        } catch (Exception ex) {

            logger.error(
                    "Career Mail Agent failed.",
                    ex
            );

        }

        logger.info("========================================");
        logger.info("Career Mail Agent Finished");
        logger.info("========================================");

        System.exit(0);
    }
}