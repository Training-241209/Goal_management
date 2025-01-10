package com.gm.goal_m.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AddGoal {
    private String objective;
    private String description;

    private Boolean status;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate startDay;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate endDay;   
}
