//package io.github.algonawaz.careermailagent.scheduler;

import io.github.algonawaz.careermailagent.service.MailScannerService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Schedules periodic scans of the Gmail inbox.
 * Performs an initial scan on startup and recurring scans
 * at the configured interval.
 */
//@Component
/*
public class MailScheduler {

    private static final Logger logger =
            LoggerFactory.getLogger(MailScheduler.class);

    private final MailScannerService mailScannerService;

    public MailScheduler(
            MailScannerService mailScannerService) {

        this.mailScannerService = mailScannerService;
    }

    @PostConstruct
    public void initialScan() {

        logger.info("Initial scan started.");

        mailScannerService.scanCareerEmails();
    }

    @Scheduled(fixedDelayString = "${scheduler.interval}")
    public void scanInbox() {

        logger.info("Scheduled scan started.");

        mailScannerService.scanCareerEmails();

    }

} */