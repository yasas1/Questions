package com.learn.streamdemo.service.impl;

import com.learn.streamdemo.domain.api.NotificationRequest;
import com.learn.streamdemo.domain.entity.Notification;
import com.learn.streamdemo.processor.SinkProcessor;
import com.learn.streamdemo.repository.NotificationRepository;
import com.learn.streamdemo.service.NotificationService;
import com.learn.streamdemo.util.NotificationHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    private static final SinkProcessor<Notification> notificationSinkProcessor = new SinkProcessor<>();

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Mono<Notification> createNotification(NotificationRequest notificationRequest) {
        NotificationHelper.validateNotificationRequest(notificationRequest);
        return Mono.just(NotificationHelper.notificationRequestToNotificationEntity(notificationRequest))
                .flatMap(this.notificationRepository::save)
                .doOnNext(notificationSinkProcessor::add)
                .doOnError(throwable -> log.error("Error on saving notification, ", throwable));
    }

    @Override
    public Flux<Notification> streamNotifications() {
        return this.notificationRepository.findAll()
                .mergeWith(notificationSinkProcessor.flux());
    }
}
