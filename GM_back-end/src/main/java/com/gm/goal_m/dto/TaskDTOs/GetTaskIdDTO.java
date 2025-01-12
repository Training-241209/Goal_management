package com.gm.goal_m.dto.TaskDTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GetTaskIdDTO {

    // @NotBlank(message = "Missing task id ")
    private Long TaskId;
    
}


