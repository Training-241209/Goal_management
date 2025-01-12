package com.gm.goal_m.dto.GoalDTOs;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateGoalDTO {

    // @NotBlank(message = "Missing goal id")
    private Long goalId;

    // @NotBlank(message = "Missing objective")
    private String objective;

    // @NotBlank(message = "Missing description")
    private String description;

    // @NotBlank(message = "Missing type")
    private String type;


    // @NotBlank(message = "Missing start date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate startDay;

    // @NotBlank(message = "Missing end date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate endDay;   
}
