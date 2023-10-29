package com.monitorexample.hashedwheeltimerdemo.process;

import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

public interface MonitorProcess extends TimerTask {
    String getEventId();
    long getDuration();
    TimeUnit getTimeUnit();
}
