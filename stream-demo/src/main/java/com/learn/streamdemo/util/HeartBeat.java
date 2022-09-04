package com.learn.streamdemo.util;

import lombok.Getter;

@Getter
public class HeartBeat {

    public static final HeartBeat INSTANCE = new HeartBeat();
    private final String type = "HeartBeat";

    private HeartBeat (){
    }

}
