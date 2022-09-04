package com.learn.streamdemo.controller;

import com.learn.streamdemo.domain.api.NotificationRequest;
import com.learn.streamdemo.domain.entity.Notification;
import com.learn.streamdemo.service.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/create")
    public Mono<Notification> createNotification(@RequestBody NotificationRequest notificationRequest) {
        return this.notificationService.createNotification(notificationRequest);
    }
}
