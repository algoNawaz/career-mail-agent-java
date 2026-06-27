package io.github.algonawaz.careermailagent.client;

import io.github.algonawaz.careermailagent.model.EmailMessage;

import java.util.List;

public interface MailClient {

    List<EmailMessage> getUnreadEmails();

}