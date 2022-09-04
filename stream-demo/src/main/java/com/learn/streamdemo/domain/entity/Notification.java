package com.learn.streamdemo.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Builder
@Document(collection = "notification")
public class Notification {
    @Id
    private String id;
    private String message;
}
