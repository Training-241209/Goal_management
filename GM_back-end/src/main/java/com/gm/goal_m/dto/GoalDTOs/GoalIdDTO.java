package com.gm.goal_m.dto.GoalDTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GoalIdDTO {
    // @NotBlank(message = "Missing date")
    private Long goalId;
    
}
