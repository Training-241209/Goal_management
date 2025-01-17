package com.gm.goal_m.dto.GenericDTOs;

import java.time.LocalDate;

import com.gm.goal_m.model.Goal;

import lombok.Data;
@Data
public class GoalDTO {
     private Long id;
    private String objective;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean status;
    private String type;

    public GoalDTO(Goal goal) {
        this.id= goal.getId();
        this.objective = goal.getObjective();
        this.description = goal.getDescription();
        this.startDate = goal.getStartDate();
        this.endDate = goal.getEndDate();
        this.status = goal.getStatus();
        this.type = goal.getType();
    }
    
}