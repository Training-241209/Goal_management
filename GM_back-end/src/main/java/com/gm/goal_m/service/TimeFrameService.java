package com.gm.goal_m.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gm.goal_m.dto.AddTaskDTO;
import com.gm.goal_m.dto.AddTimeFrameToTaskIdDTO;
import com.gm.goal_m.dto.GetTaskIdDTO;
import com.gm.goal_m.model.Task;
import com.gm.goal_m.model.TimeFrame;
import com.gm.goal_m.repository.TimeFrameRepository;

@Service
public class TimeFrameService {

    private TimeFrameRepository timeFrameRepository;
    private TaskService taskService;

    @Autowired
    public TimeFrameService(TimeFrameRepository timeFrameRepository){
        this.timeFrameRepository = timeFrameRepository;
    }
    
    public TimeFrame updateTimeFrame(TimeFrame timeFrame) {
        return timeFrameRepository.save(timeFrame);
    }

    public TimeFrame persist(TimeFrame timeFrame) {
        return timeFrameRepository.save(timeFrame);
    }

    public List<TimeFrame> getAllTimeFramesByTaskId(GetTaskIdDTO getTaskIdDTO) {
        Optional<Task> task = taskService.getTaskById(getTaskIdDTO.getTaskId());

        if(!task.isPresent()){
            throw new UnsupportedOperationException("Task NotFound");
        }
        return timeFrameRepository.findByTask(task.get());
    }

    public TimeFrame addTimeFrameByTaskId(AddTimeFrameToTaskIdDTO addTimeFrameByTaskIdDTO) {

        Optional <Task> task = taskService.getTaskById(addTimeFrameByTaskIdDTO.getTaskId());

        if(!task.isPresent()){
            throw new UnsupportedOperationException("Task NotFound");
        }

        TimeFrame timeFrame = new TimeFrame();
        timeFrame.setObjective(addTimeFrameByTaskIdDTO.getObjective());
        timeFrame.setStartTime(addTimeFrameByTaskIdDTO.getStartTime());
        timeFrame.setEndTime(addTimeFrameByTaskIdDTO.getEndTime());
        timeFrame.setTask(task.get());

        return timeFrameRepository.save(timeFrame);
    }
    
}
