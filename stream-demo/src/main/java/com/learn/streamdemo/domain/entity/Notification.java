package com.learn.streamdemo.domain.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document(collation = "notification")
public class Notification {
    @Id
    private Long id;
    private String message;
}
