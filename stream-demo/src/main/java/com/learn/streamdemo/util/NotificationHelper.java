package com.learn.streamdemo.util;

import com.learn.streamdemo.domain.api.NotificationRequest;
import com.learn.streamdemo.domain.entity.Notification;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotificationHelper {

    public static void validateNotificationRequest(NotificationRequest notificationRequest) {
        if (notificationRequest.getMessage() == null || notificationRequest.getMessage().isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Notification message is mandatory");
        }
    }

    public static Notification notificationRequestToNotificationEntity(NotificationRequest notificationRequest) {
        return Notification.builder().message(notificationRequest.getMessage()).build();
    }
}
