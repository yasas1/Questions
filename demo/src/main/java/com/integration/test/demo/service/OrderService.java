package com.integration.test.demo.service;

import com.integration.test.demo.entity.OrderEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {

    Mono<OrderEntity> createOrder(OrderEntity orderEntity);

}
