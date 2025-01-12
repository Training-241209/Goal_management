package com.gm.goal_m.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gm.goal_m.dto.GoalDTOs.UpdateGoalDTO;
import com.gm.goal_m.dto.TaskDTOs.AddTaskByGoalIdDTO;
import com.gm.goal_m.dto.TaskDTOs.UpdateTaskDTO;
import com.gm.goal_m.dto.TimeFrameDTOs.AddTimeFrameByTaskIdDTO;
import com.gm.goal_m.model.Goal;
import com.gm.goal_m.model.Task;
import com.gm.goal_m.model.User;
import com.gm.goal_m.service.TaskService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/user/goal/")
public class TaskController {

    private TaskService taskService;

   @Autowired
    public TaskController (TaskService taskService){
        this.taskService = taskService; 
    }

    @PostMapping("/task")
    public ResponseEntity<?> addTaskByGoalId(@Valid @RequestBody AddTaskByGoalIdDTO addTaskDTO) {

        try{

            if(addTaskDTO == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing request body");
            }


            Task retBody = taskService.AddTaskByGoalId(addTaskDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body( retBody);


        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Task " + e.getMessage());
        }
        
    }

    @PatchMapping("/task")
    public ResponseEntity<?> updateGoalByUser(@RequestBody UpdateTaskDTO updateTask, HttpServletRequest request) {
        try{

            Task retBody  = taskService.updateTask(updateTask);
            return ResponseEntity.status(HttpStatus.CREATED).body(retBody);
            
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update goal" + e.getMessage());
        }     
    }
     
    
}
