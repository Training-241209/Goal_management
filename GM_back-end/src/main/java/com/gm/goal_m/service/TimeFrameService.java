package com.gm.goal_m.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gm.goal_m.dto.TaskDTOs.GetTaskIdDTO;
import com.gm.goal_m.dto.TimeFrameDTOs.AddTimeFrameByTaskIdDTO;
import com.gm.goal_m.dto.TimeFrameDTOs.UpdateTimeFrameDTO;
import com.gm.goal_m.model.Task;
import com.gm.goal_m.model.TimeFrame;
import com.gm.goal_m.repository.TimeFrameRepository;

import jakarta.validation.Valid;

@Service
public class TimeFrameService {
    private TimeFrameRepository timeFrameRepository;
    private TaskService taskService;

    @Autowired
    public TimeFrameService(TimeFrameRepository timeFrameRepository, TaskService taskService){
        this.timeFrameRepository = timeFrameRepository;
        this.taskService = taskService;
    }
    
    public TimeFrame update(TimeFrame timeFrame) {
        return timeFrameRepository.save(timeFrame);
    }

    public TimeFrame persist(TimeFrame timeFrame) {
        return timeFrameRepository.save(timeFrame);
    }


    public void addTimeFrameToTask(Task task, AddTimeFrameByTaskIdDTO addTimeFrameByTaskIdDTO) {

        TimeFrame timeFrame = new TimeFrame();
        timeFrame.setObjective(addTimeFrameByTaskIdDTO.getObjective());
        timeFrame.setStartTime(addTimeFrameByTaskIdDTO.getStartTime());
        timeFrame.setEndTime(addTimeFrameByTaskIdDTO.getEndTime());
        timeFrame.setTask(task);

        persist(timeFrame);

        task.getTimeFrames().add(timeFrame);
        
        taskService.update(task);
    }

    public TimeFrame updateTimeFrame(UpdateTimeFrameDTO updateTimeFrameDTO) {
        TimeFrame timeFrame = getTimeFrameById(updateTimeFrameDTO.getTimeFrameId());
        timeFrame.setObjective(updateTimeFrameDTO.getObjective());
        timeFrame.setStartTime(updateTimeFrameDTO.getStartTime());
        timeFrame.setEndTime(updateTimeFrameDTO.getEndTime());

        return timeFrameRepository.save(timeFrame);
    

    }

    public TimeFrame getTimeFrameById(Long timeFrameId) {
        Optional <TimeFrame> timeFrameOpt = timeFrameRepository.findById(timeFrameId);
        if(!timeFrameOpt.isPresent()){
            throw new UnsupportedOperationException("Timeframe NotFound");
        }


        return timeFrameOpt.get();
    }

    public void deleteTimeFrameById(Long id) {
        timeFrameRepository.deleteById(id);
    }    
}
