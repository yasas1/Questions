package com.monitorexample.hashedwheeltimerdemo.util;

import com.monitorexample.hashedwheeltimerdemo.entity.Work;
import com.monitorexample.hashedwheeltimerdemo.entity.dto.WorkDto;
import com.monitorexample.hashedwheeltimerdemo.entity.type.WorkStatus;
import com.monitorexample.hashedwheeltimerdemo.requst.WorkRequest;

public class WorkUtil {
    private WorkUtil() {
    }

    public static Work workDtoToWorkEntity(WorkRequest workRequest, WorkStatus status) {
        return Work.builder()
                .title(workRequest.getTitle())
                .description(workRequest.getDescription())
                .durationInMinutes(workRequest.getDurationInMinutes())
                .startDateTime(System.currentTimeMillis())
                .status(status)
                .build();
    }

    public static WorkDto workEntityToWorkDto(Work work) {
        return WorkDto.builder()
                .workId(work.getWorkId())
                .title(work.getTitle())
                .description(work.getDescription())
                .durationInMinutes(work.getDurationInMinutes())
                .startDateTime(work.getStartDateTime())
                .status(work.getStatus())
                .build();
    }
}
