package com.gm.goal_m.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class AddTimeFrameByTaskIdDTO {

    private String objective;
    private Long taskId;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss")
    private LocalTime startTime;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss")
    private LocalTime endTime;
    
}
