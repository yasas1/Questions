package com.integration.test.demo.service;

import com.integration.test.demo.dto.OrderDto;
import com.integration.test.demo.entity.OrderEntity;
import reactor.core.publisher.Mono;

public interface OrderService {

    Mono<OrderEntity> createOrder(OrderDto orderDto);

    Mono<OrderEntity> getOrderById(String id);

}
