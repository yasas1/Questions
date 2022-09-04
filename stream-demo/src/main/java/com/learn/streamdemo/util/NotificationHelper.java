package com.learn.streamdemo.util;

import com.learn.streamdemo.domain.dto.NotificationDto;
import com.learn.streamdemo.domain.entity.Notification;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotificationHelper {

    public static void validateNotificationRequest(NotificationDto notificationRequest) {
        if (notificationRequest.getMessage() == null || notificationRequest.getMessage().isBlank()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Notification message is mandatory");
        }
    }

    public static Notification notificationDtoToNotificationEntity(NotificationDto notificationDto) {
        return Notification.builder().message(notificationDto.getMessage()).build();
    }
}
