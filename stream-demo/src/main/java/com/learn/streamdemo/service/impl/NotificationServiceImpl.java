package com.learn.streamdemo.service.impl;

import com.learn.streamdemo.domain.dto.NotificationDto;
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
    public Mono<Notification> createNotification(NotificationDto notificationDto) {
        NotificationHelper.validateNotificationRequest(notificationDto);
        return Mono.just(NotificationHelper.notificationDtoToNotificationEntity(notificationDto))
                .flatMap(this.notificationRepository::save)
                .doOnNext(notificationSinkProcessor::add)
                .doOnError(throwable -> log.error("Error on saving notification, ", throwable));
    }

    @Override
    public Mono<Notification> getNotificationById(String id) {
        return notificationRepository.findById(id);
    }

    @Override
    public Flux<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Flux<Notification> streamNotifications() {
        return this.notificationRepository.findAll()
                .mergeWith(notificationSinkProcessor.flux());
    }
}
