package io.github.algonawaz.careermailagent.controller;

import io.github.algonawaz.careermailagent.dto.HealthResponse;
import io.github.algonawaz.careermailagent.service.HealthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    private static final Logger logger =
            LoggerFactory.getLogger(HealthController.class);

    private final HealthService healthService;

    public HealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    @GetMapping("/health")
    public HealthResponse health() {

        logger.info("Health endpoint invoked.");

        return healthService.getHealthStatus();
    }
}