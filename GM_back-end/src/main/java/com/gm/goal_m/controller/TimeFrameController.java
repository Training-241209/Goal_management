package com.gm.goal_m.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gm.goal_m.dto.TaskDTOs.AddTimeFrameByTaskIdDTO;
import com.gm.goal_m.model.Task;
import com.gm.goal_m.service.TaskService;
import com.gm.goal_m.service.TimeFrameService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user/goal/Task")
public class TimeFrameController {
    
    private TimeFrameService timeFrameService;
    private TaskService taskService;

    public TimeFrameController (TimeFrameService timeFrameService){
        this.timeFrameService = timeFrameService;
    }

    @PostMapping("timeframe")
    public ResponseEntity<?> addTimeFrameByTaskId( @RequestBody AddTimeFrameByTaskIdDTO addTimeFrameByTaskIdDTO ) {

        try{
            if(addTimeFrameByTaskIdDTO == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing request body");


            }

            System.out.println(addTimeFrameByTaskIdDTO);

            Task task = taskService.getTaskById(addTimeFrameByTaskIdDTO.getTaskId());  
            
            timeFrameService.addTimeFrameToTask(task, addTimeFrameByTaskIdDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body("Successfull");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create TimeFrame " + e.getMessage());
        }   
    }

}
