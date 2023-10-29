package com.monitorexample.hashedwheeltimerdemo.entity.dto;

import com.monitorexample.hashedwheeltimerdemo.entity.type.WorkStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WorkDto {
    private final Long workId;
    private final String title;
    private final String description;
    private final long durationInMinutes;
    private final long startDateTime;
    private final WorkStatus status;
}
