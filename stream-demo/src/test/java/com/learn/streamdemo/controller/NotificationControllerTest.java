package com.learn.streamdemo.controller;

import com.learn.streamdemo.AbstractIntegrationTest;
import com.learn.streamdemo.domain.entity.Notification;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

@Slf4j
class NotificationControllerTest extends AbstractIntegrationTest {

    @Test
    void createNotification() {

        String requestJson = readFile("api/notification_request.json");
        String createUri = "/notifications/create";
        String findAllUri = "/notifications/all";

        webTestClient
                .post()
                .uri(createUri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(requestJson), String.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.message").isEqualTo("Test Notification Message");

        webTestClient
                .get()
                .uri(findAllUri)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Notification.class);
    }
}