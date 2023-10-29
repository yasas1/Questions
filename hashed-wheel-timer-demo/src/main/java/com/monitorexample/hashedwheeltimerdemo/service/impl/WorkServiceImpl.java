package com.monitorexample.hashedwheeltimerdemo.service.impl;

import com.monitorexample.hashedwheeltimerdemo.entity.dto.WorkDto;
import com.monitorexample.hashedwheeltimerdemo.entity.type.WorkStatus;
import com.monitorexample.hashedwheeltimerdemo.process.WorkMonitorProcess;
import com.monitorexample.hashedwheeltimerdemo.repository.WorkRepository;
import com.monitorexample.hashedwheeltimerdemo.requst.WorkRequest;
import com.monitorexample.hashedwheeltimerdemo.service.MonitorService;
import com.monitorexample.hashedwheeltimerdemo.service.WorkService;
import com.monitorexample.hashedwheeltimerdemo.util.WorkUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class WorkServiceImpl implements WorkService {

    private final MonitorService monitorService;
    private final WorkRepository workRepository;

    @Override
    public Mono<WorkDto> startTheWork(WorkRequest workRequest) {
        return this.workRepository.save(WorkUtil.workDtoToWorkEntity(workRequest, WorkStatus.STARTED)) // save to the DB
                .doOnNext(work -> this.monitorService.createAndStartMonitorProcess(new WorkMonitorProcess(WorkUtil.workEntityToWorkDto(work), this))) // create and start the timer
                .map(WorkUtil::workEntityToWorkDto);

    }

    @Override
    public Mono<WorkDto> endTheWorkById(Long workId) {
        return this.updateStatusOfWorkById(workId, WorkStatus.ENDED)
                .doOnNext(work -> this.monitorService.cancelMonitorProcess(work.getWorkId().toString()));
    }

    @Override
    public Mono<WorkDto> updateStatusOfWorkById(Long workId, WorkStatus status) {
        return this.workRepository.findById(workId)
                .flatMap(work -> this.workRepository.updateStatusByWorkId(work.getWorkId(), status.name()).thenReturn(work))
                .map(WorkUtil::workEntityToWorkDto)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NOT_EXTENDED, "Work is not found to update"))));
    }

    @Override
    public Flux<WorkDto> findAllWork() {
        return this.workRepository.findAll().map(WorkUtil::workEntityToWorkDto);
    }

    @Override
    public Mono<WorkDto> getWorkById(Long workId) {
        return this.workRepository.findById(workId)
                .map(WorkUtil::workEntityToWorkDto)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NOT_EXTENDED, "Work is not found"))));
    }
}
