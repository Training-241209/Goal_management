package com.gm.goal_m.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gm.goal_m.dto.AddGoalDTO;
import com.gm.goal_m.model.Goal;
import com.gm.goal_m.repository.GoalRepository;


@Service
public class GoalService {

private final GoalRepository goalRepository;

@Autowired
public GoalService (GoalRepository goalRepository){
    this.goalRepository = goalRepository;
}
    
public Goal createGoal(AddGoalDTO addGoalDTO) {
    Goal goal = new Goal();
    goal.setObjective(addGoalDTO.getObjective());
    goal.setDescription(addGoalDTO.getDescription());
    goal.setStartDate(addGoalDTO.getStartDay());
    goal.setEndDate(addGoalDTO.getEndDay());
    return goalRepository.save(goal);
}  

/*public List<Goal> findAllUserGoals (Long id){
    return goalRepository.findByUserId((long) id);
}*/

public Goal updateGoalEndDate(Long id, LocalDate newEndDate){
    Goal goal = getGoalById(id);
    if(goal != null){
        goal.setEndDate(newEndDate);
        goalRepository.save(goal);
        return goal;
    } else {
        return null;
    }
}

public Goal getGoalById(Long id){
    if(goalRepository.findById((long) id).isPresent()){
        return goalRepository.findById((long) id).get();
    } else {
        return null;
    }
}

public void deleteAllGoals() {
    goalRepository.deleteAll();
}

}
