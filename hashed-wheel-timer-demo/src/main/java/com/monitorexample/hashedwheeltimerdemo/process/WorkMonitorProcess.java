package com.monitorexample.hashedwheeltimerdemo.process;

import com.monitorexample.hashedwheeltimerdemo.entity.dto.WorkDto;
import com.monitorexample.hashedwheeltimerdemo.entity.type.WorkStatus;
import com.monitorexample.hashedwheeltimerdemo.service.impl.WorkServiceImpl;
import io.netty.util.Timeout;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
@AllArgsConstructor
public class WorkMonitorProcess implements MonitorProcess {

    private final WorkDto workDto;
    private final WorkServiceImpl workService;

    @Override
    public void run(Timeout timeout) throws Exception {
        // implement actions that need to be done after duration expired
        // Example: DB updates,Send notification,... etc
        this.workService.updateStatusOfWorkById(workDto.getWorkId(), WorkStatus.EXPIRED).subscribe();
        log.info("Work Id {} is expired", workDto.getWorkId());
    }

    @Override
    public String getEventId() {
        return workDto.getWorkId().toString();
    }

    @Override
    public long getDuration() {
        return workDto.getDurationInMinutes();
    }

    @Override
    public TimeUnit getTimeUnit() {
        return TimeUnit.SECONDS;
    }
}
