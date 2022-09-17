package com.learn.streamdemo.service;

import com.learn.streamdemo.domain.dto.NotificationDto;
import com.learn.streamdemo.domain.entity.Notification;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NotificationService {

    Mono<Notification> createNotification(NotificationDto notificationDto);
    Mono<Notification> getNotificationById(String id);
    Flux<Notification> getAllNotifications();
    Flux<Notification> streamNotifications();
}
