package com.gm.goal_m.dto.TimeFrameDTOs;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddTimeFrameByTaskIdDTO {

    // @NotBlank(message = "Missing Task id")
    private Long taskId;

    // @NotBlank(message = "Missing Objective")
    private String objective;

    // @NotBlank(message = "Missing start date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss")
    private LocalTime startTime;

    // @NotBlank(message = "Missing end date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss")
    private LocalTime endTime;
    
}
