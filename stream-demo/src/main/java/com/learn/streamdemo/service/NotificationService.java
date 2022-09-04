package com.learn.streamdemo.service;

import com.learn.streamdemo.domain.api.NotificationRequest;
import com.learn.streamdemo.domain.entity.Notification;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NotificationService {

    Mono<Notification> createNotification(NotificationRequest notificationRequest);
    Flux<Notification> getAllNotifications();
}
