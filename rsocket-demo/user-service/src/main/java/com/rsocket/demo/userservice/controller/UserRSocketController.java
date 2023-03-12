package com.rsocket.demo.userservice.controller;

import com.rsocket.demo.userservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
@Controller()
public class UserRSocketController {

    private final UserService userService;

    @MessageMapping("get-user-name-by-id")
    public Mono<String> getUserNameById(String id) {
        log.info("UserRSocketController : Received to route : get-user-name-by-id");
        return userService.getUserNameById(id);
    }

    @MessageMapping("get-all-users-names")
    public Flux<String> getUserAllNames() {
        log.info("UserRSocketController : Received to route : get-all-users-names");
        return userService.getAllUsesNames();
    }

    @MessageMapping("collect-unit-name")
    public Mono<Void> collectUnitName(String unitName) {
        log.info("UserRSocketController : Received to route : get-unit-names , unit name {}", unitName);
        return userService.collectUnitName(unitName);
    }
}
