package com.gm.goal_m.dto.TaskDTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskRequestDTO {

    @NotBlank(message = "Missing id")
    private Long id;
}
