package com.monitorexample.hashedwheeltimerdemo.service.impl;

import com.monitorexample.hashedwheeltimerdemo.comfiguration.WorkConfiguration;
import com.monitorexample.hashedwheeltimerdemo.process.MonitorProcess;
import com.monitorexample.hashedwheeltimerdemo.service.MonitorService;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class MonitorServiceImpl implements MonitorService {
    private final WorkConfiguration workConfiguration;
    private final Map<String, Timeout> workTimersMap = new ConcurrentHashMap<>();
    private HashedWheelTimer workTimer;

    public MonitorServiceImpl(WorkConfiguration workConfiguration) {
        this.workConfiguration = workConfiguration;
    }

    @PostConstruct
    private void init() {
        this.workTimer = new HashedWheelTimer(this.workConfiguration.getTickDuration(), TimeUnit.SECONDS);
        this.workTimer.start();
    }

    @Override
    public void createAndStartMonitorProcess(MonitorProcess monitorProcess) {
        Timeout timeout = this.workTimer.newTimeout(monitorProcess, monitorProcess.getDuration(), monitorProcess.getTimeUnit());
        synchronized (this.workTimersMap) {
            this.workTimersMap.put(monitorProcess.getEventId(), timeout);
        }
    }

    @Override
    public void cancelMonitorProcess(String eventId) {
        synchronized (this.workTimersMap) {
            Timeout timeout = this.workTimersMap.get(eventId);
            if (timeout != null) {
                timeout.cancel();
                this.workTimersMap.remove(eventId);
            }
        }
    }
}
