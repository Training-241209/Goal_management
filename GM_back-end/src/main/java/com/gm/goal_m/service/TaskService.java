package com.gm.goal_m.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gm.goal_m.Util.Enums.TaskType;
import com.gm.goal_m.dto.AddTaskDTO;
import com.gm.goal_m.dto.AddTimeFrameDTO;
import com.gm.goal_m.model.Task;
import com.gm.goal_m.model.TimeFrame;
import com.gm.goal_m.repository.TaskRepository;
import com.gm.goal_m.repository.TimeFrameRepository;

@Service
public class TaskService {
  
    private TaskRepository taskRepository;
    private TimeFrameService timeFrameService;

    @Autowired
    public TaskService(TaskRepository taskRepository, TimeFrameService timeFrameService){
        this.taskRepository = taskRepository;
        this.timeFrameService = timeFrameService;
    }

    public Optional <Task> getTask(Long id, TaskType type) {
        return taskRepository.findById(id);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task initTask (Task task) {
        return taskRepository.save(task);
    }

    public Task persistTask(AddTaskDTO addTaskDTO) {


            Task newTask = new Task();
            newTask.setName(addTaskDTO.getName());
            newTask.setDescription(addTaskDTO.getDescription());
            newTask.setType(addTaskDTO.getType());

            Task initTask = initTask(newTask);//for relation

            List <TimeFrame> timeFramesArrayList = new ArrayList<>();
            for( AddTimeFrameDTO addTimeFrameDTO : addTaskDTO.getTimeFrames()){

                LocalDateTime startTime = LocalDateTime.of(LocalDate.now(),addTimeFrameDTO.getStartTime());
                LocalDateTime endTime = LocalDateTime.of(LocalDate.now(),addTimeFrameDTO.getEndTime());

                TimeFrame timeframe = new TimeFrame();
                timeframe.setTask(initTask);
                timeframe.setObjective(addTimeFrameDTO.getObjective());
                timeframe.setStartTime(startTime);
                timeframe.setEndTime(endTime);


                System.out.println(timeframe);

                timeFramesArrayList.add(timeFrameService.persist(timeframe));
                System.out.println(timeframe);

            }

            newTask.setId(initTask.getId());
            newTask.setTimeframeS(timeFramesArrayList);

            // System.out.println(newTask);

        return taskRepository.save(newTask);
    }
    
    public void updateTask(Task task) {
        taskRepository.save(task);
    }
}
