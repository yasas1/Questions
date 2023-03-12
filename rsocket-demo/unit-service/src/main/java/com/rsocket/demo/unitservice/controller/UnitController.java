package com.rsocket.demo.unitservice.controller;

import com.rsocket.demo.unitservice.service.UnitService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/units")
public class UnitController {

    private final UnitService UnitService;

    @GetMapping(value = "/{unitId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> getUserNameForUnitId(@PathVariable String unitId) {
        return UnitService.getUserNameForUnitId(unitId);
    }

    @GetMapping(value = "/stream/users", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getUserNameForUnitId() {
        return UnitService.getAllUsersNames();
    }

    @PostMapping(value = "/name/{unitName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> collectUnitName(@PathVariable String unitName) {
        return UnitService.sendUnitNameToUserServiced(unitName)
                .thenReturn("Received");
    }
}
