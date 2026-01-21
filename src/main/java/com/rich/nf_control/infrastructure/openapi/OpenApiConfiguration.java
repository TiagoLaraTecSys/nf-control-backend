package com.rich.nf_control.infrastructure.openapi;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Value("${openapi.apiKey}")
    private String apiKey;

    @Bean
    public OpenAIClient config() {
        return OpenAIOkHttpClient.builder()
                .apiKey(apiKey)
                .build();
    }
}