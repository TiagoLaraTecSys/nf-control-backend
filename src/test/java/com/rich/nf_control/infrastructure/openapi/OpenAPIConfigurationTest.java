package com.rich.nf_control.infrastructure.openapi;

import com.openai.client.OpenAIClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OpenAPIConfigurationTest {


    private OpenApiConfiguration openApiConfiguration;

    @BeforeEach
    void setup() {
        openApiConfiguration = new OpenApiConfiguration();
        ReflectionTestUtils.setField(openApiConfiguration, "apiKey", "123");
    }

    @Test
    void deve_configurar_openapi_client() {

        OpenAIClient client = openApiConfiguration.config();

        assertNotNull(client);
    }
}