package com.integration.test.demo.controller;

import com.integration.test.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderServiceController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/welcome")
    public Mono<String> welcomeOrderEvents(){
        return Mono.just("Welcome To Order Service");
    }


}
