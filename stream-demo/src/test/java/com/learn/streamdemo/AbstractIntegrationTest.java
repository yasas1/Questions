package com.learn.streamdemo;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;
import java.nio.charset.StandardCharsets;

@Testcontainers
@AutoConfigureWebTestClient(timeout = "3600000")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractIntegrationTest {

    @Autowired
    protected WebTestClient webTestClient;

    @Container
    protected final static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0.12")
            .withReuse(true);

    static {
        mongoDBContainer.start();
    }

    @DynamicPropertySource
    protected static void dynamicPropertiesProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.default.mongodb.host", mongoDBContainer::getHost);
        registry.add("spring.data.default.mongodb.database", mongoDBContainer::getHost);
        registry.add("spring.data.default.mongodb.port", mongoDBContainer::getHost);
    }

    @SneakyThrows
    protected String readFile(String responseFile) {
        return IOUtils.toString(new ClassPathResource(responseFile).getInputStream(), StandardCharsets.UTF_8);
    }

}
