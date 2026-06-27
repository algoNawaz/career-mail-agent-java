package io.github.algonawaz.careermailagent.service.detector;

import io.github.algonawaz.careermailagent.constants.CareerConstants;
import io.github.algonawaz.careermailagent.model.EmailMessage;
import org.springframework.stereotype.Component;

@Component
public class KeywordCareerDetector implements CareerDetector {

    @Override
    public boolean isCareerEmail(EmailMessage email) {

        String text = (
                email.getFrom() + " " +
                        email.getSubject() + " " +
                        email.getSnippet()
        ).toLowerCase();

        boolean keywordMatch =
                CareerConstants.KEYWORDS
                        .stream()
                        .anyMatch(text::contains);

        boolean companyMatch =
                CareerConstants.COMPANIES
                        .stream()
                        .anyMatch(text::contains);

        return keywordMatch || companyMatch;
    }

}