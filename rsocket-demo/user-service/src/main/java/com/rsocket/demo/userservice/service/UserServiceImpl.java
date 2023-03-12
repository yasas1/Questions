package com.rsocket.demo.userservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.Duration;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    public Mono<String> getUserNameById(String id) {
        return Mono.just("Example User name 1");
    }

    @Override
    public Flux<String> getAllUsesNames() {
        return Flux.interval(Duration.ofSeconds(5))
                .map(i -> "Example User name " + i);
    }

    @Override
    public Mono<Void> collectUnitName(String unitName) {
        log.info("User Service received unit name {}", unitName);
        return Mono.empty();
    }
}
