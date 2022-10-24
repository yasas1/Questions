package com.integration.test.demo.controller;

import com.integration.test.demo.dto.OrderDto;
import com.integration.test.demo.entity.OrderEntity;
import com.integration.test.demo.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderServiceController {

    private OrderService orderService;

    @PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<OrderEntity> createOrder(@RequestBody OrderDto orderDto){
        return orderService.createOrder(orderDto);
    }

    @GetMapping("/{id}")
    public Mono<OrderEntity> getOrderById(@PathVariable(name = "id") String id){
        return orderService.getOrderById(id);
    }

}
