package com.gm.goal_m.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gm.goal_m.dto.TaskRequestDTO;
import com.gm.goal_m.model.Task;
import com.gm.goal_m.service.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class TaskController {

    // @Autowired
    private TaskService taskService;

    @GetMapping("")
    public String welcone() {
        return "welcome to goal management api";
        
    }
    @GetMapping("/task")
    public ResponseEntity<?> getTask(@RequestBody TaskRequestDTO task) {

        try{

            if(task == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing request body");
            }

            Optional <Task> retValue = taskService.getTask(task.getId(), task.getType());

            if(!retValue.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task Not found");
            }
            
            return ResponseEntity.status(HttpStatus.FOUND).body(retValue.get());


        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get Task " + e.getMessage());
        }
        
        
    }
    @GetMapping("/tasks")
    public ResponseEntity<?> getAllTasks() {

        try{

            List <Task> retValue = taskService.getAllTasks();
            return ResponseEntity.status(HttpStatus.FOUND).body(retValue);


        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get Task: " + e.getMessage());
        }
        
        
    }

    
}
