package com.gm.goal_m.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
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

            List <TimeFrame> timeFramesArrayList = initTask.getTimeFrames();
            for( AddTimeFrameDTO addTimeFrameDTO : addTaskDTO.getTimeFrames()){

                LocalTime startTime = addTimeFrameDTO.getStartTime();
                LocalTime endTime = addTimeFrameDTO.getEndTime();

                TimeFrame timeFrame = new TimeFrame();
                timeFrame.setTask(initTask);
                timeFrame.setObjective(addTimeFrameDTO.getObjective());
                timeFrame.setStartTime(startTime);
                timeFrame.setEndTime(endTime);


                System.out.println(timeFrame);

                timeFramesArrayList.add(timeFrameService.persist(timeFrame));
                System.out.println(timeFrame);

            }

            // newTask.setId(initTask.getId());
            // newTask.setTimeFrames(timeFramesArrayList);

            // System.out.println(newTask);

        return taskRepository.save(initTask);
    }
    
    public void updateTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
}
