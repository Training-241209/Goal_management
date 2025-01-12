package com.gm.goal_m.dto.TaskDTOs;

import com.gm.goal_m.Util.Enums.Task.TaskType;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddTaskByGoalIdDTO {

    // @NotBlank(message = "Missing goal id")
    private Long goalId;

    // @NotBlank(message = "Missing name")
    private String name;

    // @NotBlank(message = "Missing description")
    private String description;

    // @NotBlank(message = "Missing goal type GOAL or ROUTINE")
    private TaskType type = TaskType.GOAL;

}
