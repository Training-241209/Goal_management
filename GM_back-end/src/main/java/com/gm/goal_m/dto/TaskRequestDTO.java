package com.gm.goal_m.dto;

import com.gm.goal_m.Util.Enums.TaskType;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskRequestDTO {

    @NotBlank(message = "Missing id")
    private Long id;
}
