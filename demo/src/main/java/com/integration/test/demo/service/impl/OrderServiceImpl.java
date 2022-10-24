package com.integration.test.demo.service.impl;


import com.integration.test.demo.dto.OrderDto;
import com.integration.test.demo.entity.OrderEntity;
import com.integration.test.demo.repository.OrderRepository;
import com.integration.test.demo.service.OrderService;
import com.integration.test.demo.util.OrderUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;


@Slf4j
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;

    @Override
    public Mono<OrderEntity> createOrder(OrderDto orderDto) {
        this.validateOrderData(orderDto);
        return orderRepository.save(OrderUtil.mapOrderDataToOrderEntity(orderDto));
    }

    @Override
    public Mono<OrderEntity> getOrderById(String id) {
        return orderRepository.findByBId(id);
    }


    private void validateOrderData(OrderDto orderDto) {
        if (orderDto.getUserName() == null || orderDto.getUserName().isBlank()) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user name");
        }
        if (orderDto.getEmail() == null || orderDto.getEmail().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user email");
        }
        if (orderDto.getItemCode() == null || orderDto.getItemCode().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid item code");
        }
        if (orderDto.getOrderStatus() == null || orderDto.getOrderStatus().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid order status");
        }
        if (orderDto.getOrderedDateTime() == 0) {
            orderDto.setOrderedDateTime(System.currentTimeMillis());
        }
    }

}
