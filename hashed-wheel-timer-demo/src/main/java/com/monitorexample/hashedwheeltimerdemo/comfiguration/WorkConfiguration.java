package com.monitorexample.hashedwheeltimerdemo.comfiguration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class WorkConfiguration {
    @Value("${timer.tickDuration:30}")
    private int tickDuration;
}
