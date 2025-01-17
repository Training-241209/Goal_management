package com.gm.goal_m.service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gm.goal_m.model.Goal;
import com.gm.goal_m.model.Task;
import com.gm.goal_m.model.TimeFrame;
import com.gm.goal_m.model.User;


@Service
public class MailSchedulerService {

    UserService userService;
    TaskService taskService;
    TimeFrameService timeFrameService;
    MailSenderService mailSenderService;
    GoalService goalService;

    public MailSchedulerService(UserService userService, TaskService taskService,TimeFrameService timeFrameService, MailSenderService mailSenderService,  GoalService goalService){

        this.userService = userService;
        this.taskService = taskService;
        this.timeFrameService = timeFrameService;
        this.mailSenderService = mailSenderService;
        this.goalService = goalService;

    }


    public void sendEmails() {
        System.out.println("schedular is working/n/n");


        List<User> users = userService.getAllUsers();

        for (User user: users){
            LocalTime now = LocalTime.now();

            if(user.getGoals().isEmpty())  continue;

            for(Goal goal : user.getGoals()){

                if(goal.getTasks().isEmpty())  continue;

                for(Task task : goal.getTasks()){     

                    if(task.getTimeFrames().isEmpty()) continue;
         

                    for(TimeFrame timeFrame : task.getTimeFrames()){
    
                        Duration duration = Duration.between(timeFrame.getStartTime(), now);
    
                        if (!duration.isNegative() && duration.toMinutes() <= 1 && !timeFrame.getStatus()) {

    
                            StringBuilder sb = new StringBuilder();
                            sb.append("Hi " + user.getFirstName()).append(",").append("\n");
                            sb.append("Goal: " + goal);
                            sb.append("Task name: " + task.getName()).append("\n");
                            sb.append("Start time: " + timeFrame.getStartTime()).append("\n");
                            sb.append("End time: " + timeFrame.getEndTime()).append("\n");    
                            
                            mailSenderService.sendNewMail(user.getEmail(), task.getName(), sb.toString());
    
                            System.out.println("timeframe present");
                        }
                    }
    
                }


            }   
   

         }

    
   
    }

    
    
}
