package com.monitorexample.hashedwheeltimerdemo.service;

import com.monitorexample.hashedwheeltimerdemo.process.MonitorProcess;

public interface MonitorService {
    void createAndStartMonitorProcess(MonitorProcess monitorProcess);
    void cancelMonitorProcess(String eventId);
}
