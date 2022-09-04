package com.learn.streamdemo.repository;

import com.learn.streamdemo.domain.entity.Notification;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends ReactiveMongoRepository<Notification,String> {
}
