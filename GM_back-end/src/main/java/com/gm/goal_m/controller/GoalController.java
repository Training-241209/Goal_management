package com.gm.goal_m.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gm.goal_m.dto.AddGoalDTO;
import com.gm.goal_m.dto.AddTaskDTO;
import com.gm.goal_m.dto.TaskRequestDTO;
import com.gm.goal_m.model.Goal;
import com.gm.goal_m.model.Task;
import com.gm.goal_m.model.User;
import com.gm.goal_m.service.GoalService;
import com.gm.goal_m.service.JwtService;
import com.gm.goal_m.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/api")
public class GoalController {

    private GoalService goalService;
    private JwtService jwtService;
    private UserService userService;

    public GoalController (GoalService goalService, JwtService jwtService, UserService userService){
        this.goalService = goalService;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/goal")
    public ResponseEntity<?> addGoal(@RequestBody AddGoalDTO addGoalDTO, HttpServletRequest request) {

        try{
            if(addGoalDTO == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing request body");
            }

            // Long userId = Long.parseLong(String.valueOf(request.getAttribute("userId")));
            // User worker = userService.findUserById(userId);

            // User user = jwtService.decodeTokenById(request.getHeader("Authorization"));

            // System.out.println(user);



         

            Goal retBody = goalService.createGoal(addGoalDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(retBody);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Boal: " + e.getMessage());
        }     
    }
    

    @PostMapping("/goal/task")
    public ResponseEntity<?> addTaskToGoal(@RequestBody AddGoalDTO addGoalDTO) {
        try{
            if(addGoalDTO == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing request body");
            }
            Goal retBody = goalService.createGoal(addGoalDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body( retBody);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Boal: " + e.getMessage());
        }   
    }

    @DeleteMapping("/goals")
    public ResponseEntity<?> deleteAllGoals() {

        try{
            goalService.deleteAllGoals();
            return ResponseEntity.status(HttpStatus.CREATED).body("Deleted Successfull");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Delete Goal" + e.getMessage());
        }
        
    }

    @GetMapping("/goals")
    public ResponseEntity<?> getAllGoals() {

        try{

            List <Goal> retValue = goalService.getAllTasks();
            return ResponseEntity.status(HttpStatus.FOUND).body(retValue);


        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get all goals: " + e.getMessage());
        }
        
    }

    
}
