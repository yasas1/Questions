package com.monitorexample.hashedwheeltimerdemo.entity.type;

import lombok.Getter;

@Getter
public enum WorkStatus {
    STARTED("Started"),
    ENDED("Ended"),
    EXPIRED("Expired");

    private String name;

    WorkStatus(String name) {
        this.name = name;
    }
}
