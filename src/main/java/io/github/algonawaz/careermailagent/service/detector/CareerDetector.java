package io.github.algonawaz.careermailagent.service.detector;

import io.github.algonawaz.careermailagent.model.EmailMessage;

public interface CareerDetector {

    boolean isCareerEmail(EmailMessage email);

}