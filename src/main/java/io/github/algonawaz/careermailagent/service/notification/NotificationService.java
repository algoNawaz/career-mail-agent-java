package io.github.algonawaz.careermailagent.service.notification;

import io.github.algonawaz.careermailagent.model.EmailMessage;

public interface NotificationService {

    void sendCareerNotification(EmailMessage email);

}