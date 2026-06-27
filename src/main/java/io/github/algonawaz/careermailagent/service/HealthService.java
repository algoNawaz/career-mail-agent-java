package io.github.algonawaz.careermailagent.service;

import io.github.algonawaz.careermailagent.dto.HealthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HealthService {

    private static final Logger logger =
            LoggerFactory.getLogger(HealthService.class);

    public HealthResponse getHealthStatus() {

        logger.info("Preparing application health response.");

        return new HealthResponse("UP");
    }
}