package com.gm.goal_m.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.gm.goal_m.repository.GoalRepository;

public class GoalService {

private final GoalRepository goalRepository;

@Autowired
public GoalService (GoalRepository goalRepository){
    this.goalRepository = goalRepository;
}
    
}
