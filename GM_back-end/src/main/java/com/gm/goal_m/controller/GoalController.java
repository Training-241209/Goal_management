package com.gm.goal_m.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gm.goal_m.dto.GoalDTOs.AddGoalDTO;
import com.gm.goal_m.dto.GoalDTOs.GoalIdDTO;
import com.gm.goal_m.dto.GoalDTOs.UpdateGoalDTO;
import com.gm.goal_m.dto.TaskDTOs.AddTaskByGoalIdDTO;
import com.gm.goal_m.dto.TaskDTOs.TaskRequestDTO;
import com.gm.goal_m.model.Goal;
import com.gm.goal_m.model.Task;
import com.gm.goal_m.model.User;
import com.gm.goal_m.service.GoalService;
import com.gm.goal_m.service.JwtService;
import com.gm.goal_m.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user/")
public class GoalController {

    private GoalService goalService;
    private JwtService jwtService;
    private UserService userService;

     @Autowired
    public GoalController (GoalService goalService, UserService userService){
        this.goalService = goalService;
        this.userService = userService;
    }

    @PostMapping("/goal")
    public ResponseEntity<?> addGoalByUser(@RequestBody AddGoalDTO addGoalDTO, HttpServletRequest request) {
        try{

            Long userId = (long) 1;
            // Long userId = jwtService.getUserId(request.getHeader("Authorization"));

            User user = userService.findUserById(userId);

            Goal retBody  = goalService.addGoalByUser(user, addGoalDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(retBody);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Goal: " + e.getMessage());
        }     
    }

    @PatchMapping("/goal")
    public ResponseEntity<?> updateGoalByUser(@RequestBody UpdateGoalDTO addGoalDTO, HttpServletRequest request) {
        try{

            Long userId = (long) 1;
            // Long userId = jwtService.getUserId(request.getHeader("Authorization"));

            User user = userService.findUserById(userId);

            Goal retBody  = goalService.updateGoalByUser(user, addGoalDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(retBody);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Goal: " + e.getMessage());
        }     
    }

    @DeleteMapping("/goal")
    public ResponseEntity<?> deleteGoalById(@RequestBody GoalIdDTO goaIdDTO){

        try{
            goalService.deleteGoalById(goaIdDTO.getGoalId());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted goal Successfull");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Delete Goal " + e.getMessage());
        }
        
    }

    @GetMapping("/goals")
    public ResponseEntity<?> getAllGoals(HttpServletRequest request) {
        try{
            Long userId = (long) 1;
            // Long userId = jwtService.getUserId(request.getHeader("Authorization"));

            User user = userService.findUserById(userId);

            List <Goal> retValue = goalService.getGoalsByUser(user);
            return ResponseEntity.status(HttpStatus.FOUND).body(retValue);


        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get all goals: " + e.getMessage());
        }
     
    }

    
}
