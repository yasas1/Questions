package com.rsocket.demo.unitservice.service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
@Service
public class UnitServiceImpl implements UnitService {

    private final RSocketRequester rSocketRequester;

    // messaging: request-response
    @Override
    public Mono<String> getUserNameForUnitId(String unitId) {
        return rSocketRequester.route("get-user-name-by-id")
                .data("1")
                .retrieveMono(String.class)
                .doOnNext(data -> log.info("request- response data from rout : get-user-name-by-id , data {}", data))
                .doOnError(throwable -> log.error("Error in reading data from rout : get-user-name-by-id", throwable));
    }

    // messaging: request-stream
    @Override
    public Flux<String> getAllUsersNames() {
        return rSocketRequester.route("get-all-users-names")
                .retrieveFlux(String.class)
                .doOnNext(data -> log.info("request-stream data from rout : get-all-users-names , data {}", data))
                .doOnError(throwable -> log.error("Error in reading data from rout : get-user-name-by-id", throwable));
    }

    // messaging: fire-and-forget
    @Override
    public Mono<Void> sendUnitNameToUserServiced(String unitName) {
        return rSocketRequester.route("collect-unit-name")
                .data(unitName)
                .send()
                .doOnNext(data -> log.info("fire-and-forgot from rout : get-all-users-names , data {}", data))
                .doOnError(throwable -> log.error("Error in sending data from rout : get-user-name-by-id", throwable));
    }
}
