package com.monitorexample.hashedwheeltimerdemo.requst;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class WorkRequest {
    @NotNull(message = "Title is mandatory")
    @NotBlank(message = "Title is mandatory")
    private String title;
    private String description;
    @DurationConstraint
    private long durationInMinutes;
}
