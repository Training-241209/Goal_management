package com.gm.goal_m.dto.TaskDTOs;

import java.util.ArrayList;

import com.gm.goal_m.Util.Enums.Task.TaskType;
import com.gm.goal_m.dto.TimeFrameDTOs.AddTimeFrameDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

enum Type {
    GOAL,
    ROUTINE,
}

@Data
public class AddTaskByGoalIdDTO {

    private Long goalId;

    @NotBlank(message = "Missing name")
    private String name;

    @NotBlank(message = "Missing description")
    private String description;

    private TaskType type = TaskType.GOAL;
    private ArrayList <AddTimeFrameDTO> timeFrames;

}
