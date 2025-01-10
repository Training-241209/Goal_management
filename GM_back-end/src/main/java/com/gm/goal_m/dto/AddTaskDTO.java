package com.gm.goal_m.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gm.goal_m.Util.Enums.TaskType;
import com.gm.goal_m.model.TimeFrame;

import lombok.Data;

enum Type {
    GOAL,
    ROUTINE,
}

@Data
public class AddTaskDTO {
    private String name;
    private String description;
    private TaskType type;
    private ArrayList <AddTimeFrameDTO> timeFrames;

}
