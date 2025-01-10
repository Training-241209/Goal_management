package com.gm.goal_m.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gm.goal_m.dto.UserLoginRequest;
import com.gm.goal_m.dto.UserRequestRegDTO;
import com.gm.goal_m.model.User;
import com.gm.goal_m.service.JwtService;
import com.gm.goal_m.service.UserService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @Autowired
    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@ Valid @RequestBody UserRequestRegDTO userRequestRegDTO) {
        try {
            userService.registerUser(userRequestRegDTO);
            return ResponseEntity.ok().body("User succesfully registered");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("User not registered at this time");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@Valid @RequestBody UserLoginRequest userLoginRequest) {
        String theToken = "";
        User user = new User(userLoginRequest.getEmail(), userLoginRequest.getPassword(), 
        userLoginRequest.getFirstName(), userLoginRequest.getLastName());
        if (userService.canLogIn(userLoginRequest)) {
            theToken = jwtService.generateToken(user);
            return ResponseEntity.ok().body(theToken);
        } else if (userService.findUserByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(401).body("The password is incorrect");
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    @GetMapping("/allusers")
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = new ArrayList<>();
        users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }
}
