package com.gm.goal_m.service;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gm.goal_m.dto.TaskDTOs.AddTaskByGoalIdDTO;
import com.gm.goal_m.dto.TaskDTOs.AddTimeFrameByTaskIdDTO;
import com.gm.goal_m.dto.TimeFrameDTOs.AddTimeFrameDTO;
import com.gm.goal_m.model.Goal;
import com.gm.goal_m.model.Task;
import com.gm.goal_m.model.TimeFrame;
import com.gm.goal_m.repository.TaskRepository;

@Service
public class TaskService {
  
    private TaskRepository taskRepository;
    private TimeFrameService timeFrameService;

    @Autowired
    private GoalService goalService;

    @Autowired
    public TaskService(TaskRepository taskRepository, TimeFrameService timeFrameService){
        this.taskRepository = taskRepository;
        this.timeFrameService = timeFrameService;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task initTask (Task task) {
        return taskRepository.save(task);
    }

    public Task AddTaskByGoalId (AddTaskByGoalIdDTO addTaskDTO) {
            
            Goal goal = goalService.getGoalById(addTaskDTO.getGoalId());
            
            Task newTask = new Task();
            newTask.setName(addTaskDTO.getName());
            newTask.setDescription(addTaskDTO.getDescription());
            newTask.setType(addTaskDTO.getType());
            newTask.setGoal(goal);

            Task initTask = initTask(newTask);//initialize task to return task with id 

            for( AddTimeFrameDTO addTimeFrameDTO : addTaskDTO.getTimeFrames()){

                LocalTime startTime = addTimeFrameDTO.getStartTime();
                LocalTime endTime = addTimeFrameDTO.getEndTime();

                TimeFrame timeFrame = new TimeFrame();
                timeFrame.setTask(initTask);
                timeFrame.setObjective(addTimeFrameDTO.getObjective());
                timeFrame.setStartTime(startTime);
                timeFrame.setEndTime(endTime);
                timeFrame.setTask(initTask);

                initTask.getTimeFrames().add(timeFrameService.persist(timeFrame));
           

            }

            goal.getTasks().add(initTask);
            goalService.update(goal);

        return taskRepository.save(initTask);
    }
    
    public void updateTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }

    public Task getTaskById(Long id) {

        Optional <Task> taskOpt = taskRepository.findById(id);
        
        if(!taskOpt.isPresent()){
            throw new UnsupportedOperationException("Task NotFound");
        }
        return  taskOpt.get();
    }

}
