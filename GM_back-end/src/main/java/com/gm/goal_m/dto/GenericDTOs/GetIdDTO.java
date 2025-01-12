package com.gm.goal_m.dto.GenericDTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GetIdDTO {
    @NotBlank(message = "Missing date")
    private Long id;    
}
