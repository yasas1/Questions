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

    private final UnitService unitService;

    // to test request-response
    @GetMapping(value = "/{unitId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> getUserNameForUnitId(@PathVariable String unitId) {
        return unitService.getUserNameForUnitId(unitId);
    }

    // to test request-stream
    @GetMapping(value = "/stream/users", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getUserNameForUnitId() {
        return unitService.getAllUsersNames();
    }

    // to test fire-and-forget
    @PostMapping(value = "/name/{unitName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> collectUnitName(@PathVariable String unitName) {
        return unitService.sendUnitNameToUserServiced(unitName)
                .thenReturn("Received");
    }
}
