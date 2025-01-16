package com.gm.goal_m.service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gm.goal_m.model.Task;
import com.gm.goal_m.model.TimeFrame;
import com.gm.goal_m.model.User;


@Service
public class MailSchedulerService {

    UserService userService;
    TaskService taskService;
    TimeFrameService timeFrameService;
    MailSenderService mailSenderService;

    public MailSchedulerService(UserService userService, TaskService taskService, TimeFrameService timeFrameService, MailSenderService mailSenderService){
        this.userService = userService;
        this.taskService = taskService;
        this.timeFrameService = timeFrameService;
        this.mailSenderService = mailSenderService;

    }


    public void sendEmails() {
        System.out.println("schedular is working");



        // List<User> users = userService.getAllUsers();

        // for (User user: users){
        //     LocalTime now = LocalTime.now();

        //     System.out.println(user.getEmail());
            // List <Task> tasks = taskService.getAllTasks();

            // for(Task task: tasks){
            //     List <TimeFrame> timeframes = timeFrameService.getAllTimeFrames();

            //     for(TimeFrame timeFrame : timeframes){

            //         Duration duration = Duration.between(timeFrame.getStartTime(), now);

            //         if (!duration.isNegative() && duration.toMinutes() <= 10 && !timeFrame.getStatus()) {

            //             StringBuilder sb = new StringBuilder();
            //             sb.append("Hi " + user.getFirstName()).append(",").append("\n");
            //             sb.append("Your Scheduled Time is about to begin").append("\n");
            //             sb.append("Task name: " + task.getName()).append("\n");
            //             sb.append("Start time: " + timeFrame.getStartTime()).append("\n");
            //             sb.append("End time: " + timeFrame.getEndTime()).append("\n");    
                        
            //             // mailSenderService.sendNewMail(user.getEmail(), task.getName(), sb.toString());

            //             System.out.println(sb.toString());
            //         }


            //         timeFrame.getStartTime();

            //     }

            // }


        
   

        //  }

    
   
    }

    
    
}
