package com.rsocket.demo.unitservice.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UnitService {
    Mono<String> getUserNameForUnitId(String unitId);
    Flux<String> getAllUsersNames();
    Mono<Void> sendUnitNameToUserServiced(String unitName);
}
