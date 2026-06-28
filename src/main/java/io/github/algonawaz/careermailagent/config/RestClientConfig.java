package io.github.algonawaz.careermailagent.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    @Qualifier("gmailRestClient")
    public RestClient gmailRestClient() {

        return RestClient.builder()
                .baseUrl("https://gmail.googleapis.com/gmail/v1")
                .build();
    }

    @Bean
    @Qualifier("telegramRestClient")
    public RestClient telegramRestClient() {

        return RestClient.builder()
                .baseUrl("https://api.telegram.org")
                .build();
    }
}