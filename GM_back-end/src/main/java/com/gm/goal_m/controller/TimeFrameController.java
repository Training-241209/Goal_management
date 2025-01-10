package com.gm.goal_m.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gm.goal_m.dto.AddTaskDTO;
import com.gm.goal_m.dto.AddTimeFrameByTaskIdDTO;
import com.gm.goal_m.model.Task;
import com.gm.goal_m.model.TimeFrame;
import com.gm.goal_m.service.TaskService;
import com.gm.goal_m.service.TimeFrameService;

@Controller
@RequestMapping("/api")
public class TimeFrameController {
    private TimeFrameService timeFrameService;


    public TimeFrameController (TimeFrameService timeFrameService){
        this.timeFrameService = timeFrameService;
    }


    @GetMapping("/timesframes/task")
    public ResponseEntity<?> getAllTimeFramesByTaskId(@RequestBody AddTimeFrameByTaskIdDTO addTimeFrameByTaskIdDTO) {

        try{

            List <TimeFrame> retValue = timeFrameService.getAllTimeFramesByTaskId(addTimeFrameByTaskIdDTO);
            return ResponseEntity.status(HttpStatus.FOUND).body(retValue);


        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get Timeframe: " + e.getMessage());
        }
        
    }
    
   @PostMapping("/timeframe")
    public ResponseEntity<?> addTimeFrameByTaskId(@RequestBody AddTimeFrameByTaskIdDTO addTimeFrameByTaskIdDTO) {

        try{

            if(addTimeFrameByTaskIdDTO== null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing request body");
            }

        
            TimeFrame retBody = timeFrameService.addTimeFrameByTaskId(addTimeFrameByTaskIdDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Added timeframe Successfully to Task: "+retBody );


        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Timeframe " + e.getMessage());
        }
        
    }


}
