package com.monitorexample.hashedwheeltimerdemo.service;

import com.monitorexample.hashedwheeltimerdemo.entity.dto.WorkDto;
import com.monitorexample.hashedwheeltimerdemo.entity.type.WorkStatus;
import com.monitorexample.hashedwheeltimerdemo.requst.WorkRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WorkService {
    Mono<WorkDto> startTheWork(WorkRequest workRequest);
    Mono<WorkDto> endTheWorkById(Long workId);
    Mono<WorkDto> updateStatusOfWorkById(Long workId, WorkStatus status);

    Flux<WorkDto> findAllWork();

    Mono<WorkDto> getWorkById(Long workId);
}
