package com.gm.goal_m.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gm.goal_m.dto.UserRequestRegDTO;
import com.gm.goal_m.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired 
    public UserController (UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRequestRegDTO userRequestRegDTO){
        try {
            userService.registerUser(userRequestRegDTO);
            return ResponseEntity.ok().body("User succesfully registered");
        } catch (Exception e){
            return ResponseEntity.status(400).body("The user cannot be registered");
        }
    }
    
}
