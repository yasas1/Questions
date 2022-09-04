package com.learn.streamdemo.controller;

import com.learn.streamdemo.domain.api.NotificationRequest;
import com.learn.streamdemo.domain.entity.Notification;
import com.learn.streamdemo.service.NotificationService;
import com.learn.streamdemo.util.HeartBeat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Notification> createNotification(@RequestBody NotificationRequest notificationRequest) {
        return this.notificationService.createNotification(notificationRequest);
    }

    @GetMapping(path = "/streams", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Object> streamNotification(){
        return Flux.interval(Duration.ofSeconds(10))
                .map(i -> (Object) HeartBeat.INSTANCE)
                .mergeWith(this.notificationService.streamNotifications());
    }
}
