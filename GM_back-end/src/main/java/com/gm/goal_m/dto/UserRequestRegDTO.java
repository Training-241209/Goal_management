package com.gm.goal_m.dto;

import lombok.Data;

@Data
public class UserRequestRegDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
