package com.integration.test.demo.service.impl;


import com.integration.test.demo.dto.OrderDto;
import com.integration.test.demo.entity.OrderEntity;
import com.integration.test.demo.repository.OrderRepository;
import com.integration.test.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Slf4j
@Service
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public Mono<OrderEntity> createOrder(OrderEntity orderEntity) {
        return Mono.empty();
    }



    private boolean validateOrderData(OrderDto orderDto) {
        if (orderDto.getUserName() == null || orderDto.getUserName().isBlank()) {
            return false;
        }
        if (orderDto.getEmail() == null || orderDto.getEmail().isBlank()) {
            return false;
        }
        if (orderDto.getItemCode() == null || orderDto.getItemCode().isBlank()) {
            return false;
        }
        if (orderDto.getOrderStatus() == null || orderDto.getOrderStatus().isBlank()) {
            return false;
        }
        if (orderDto.getOrderedDateTime() == 0) {
            orderDto.setOrderedDateTime(System.currentTimeMillis());
        }
        return true;
    }

}
