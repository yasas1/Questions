package com.monitorexample.hashedwheeltimerdemo.entity;

import com.monitorexample.hashedwheeltimerdemo.entity.type.WorkStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Data
@Table(name = "public.work")
public class Work {
    @Id
    @Column(value = "workid")
    private Long workId;
    @Column(value = "title")
    private String title;
    @Column(value = "description")
    private String description;
    @Column(value = "duration")
    private long durationInMinutes;
    @Column(value = "startdatetime")
    private long startDateTime;
    @Column(value = "status")
    private WorkStatus status;
}
