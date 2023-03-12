package com.rsocket.demo.userservice.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<String> getUserNameById(String id);
    Flux<String> getAllUsesNames();
    Mono<Void> collectUnitName(String unitName);
}
