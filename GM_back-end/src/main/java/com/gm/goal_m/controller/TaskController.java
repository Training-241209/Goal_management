package com.gm.goal_m.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gm.goal_m.dto.AddTaskDTO;
import com.gm.goal_m.dto.TaskRequestDTO;
import com.gm.goal_m.model.Task;
import com.gm.goal_m.model.TimeFrame;
import com.gm.goal_m.service.TaskService;
import com.gm.goal_m.service.TimeFrameService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class TaskController {

    private TaskService taskService;

   @Autowired
    public TaskController (TaskService taskService){
        this.taskService = taskService; 
    }

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



            Optional <Task> retValue = taskService.getTaskById(task.getId());

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

    @PostMapping("/task")
    public ResponseEntity<?> addTask(@RequestBody AddTaskDTO addTaskDTO) {

        try{

            if(addTaskDTO == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing request body");
            }

           
            System.out.println(addTaskDTO.getTimeFrames().get(0));
            Task retBody = taskService.persistTask(addTaskDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body( retBody );


        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Task " + e.getMessage());
        }
        
    }

    @DeleteMapping("/tasks")
    public ResponseEntity<?> deleteTask() {

        try{

            taskService.deleteAllTasks();
            return ResponseEntity.status(HttpStatus.CREATED).body("Delete Successfull");


        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Delete Task" + e.getMessage());
        }
        
    }

    
}
