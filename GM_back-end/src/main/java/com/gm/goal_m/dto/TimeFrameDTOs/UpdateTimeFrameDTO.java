package com.gm.goal_m.dto.TimeFrameDTOs;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateTimeFrameDTO {

    @NotNull(message = "Missing timeframe id")
    @Min(1)
    private Long timeFrameId;

    @NotBlank(message = "Missing Objective")
    private String objective;

    @NotNull(message = "Missing start time")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss")
    private LocalTime startTime;

    @NotNull(message = "Missing end time")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss")
    private LocalTime endTime;

    @NotNull(message = "Missing status")
    private Boolean status;
    
}
