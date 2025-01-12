package com.gm.goal_m.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gm.goal_m.dto.GoalDTOs.AddGoalDTO;
import com.gm.goal_m.dto.GoalDTOs.UpdateGoalDTO;
import com.gm.goal_m.model.Goal;
import com.gm.goal_m.model.User;
import com.gm.goal_m.repository.GoalRepository;


@Service
public class GoalService {

    private  GoalRepository goalRepository;
    private UserService userService;

   
    public GoalService (GoalRepository goalRepository, UserService userService){
        this.goalRepository = goalRepository;
        this.userService = userService;
    }
        
    public Goal createGoal(AddGoalDTO addGoalDTO) {
        Goal goal = new Goal();
        goal.setObjective(addGoalDTO.getObjective());
        goal.setDescription(addGoalDTO.getDescription());
        goal.setStartDate(addGoalDTO.getStartDay());
        goal.setEndDate(addGoalDTO.getEndDay());

        return goalRepository.save(goal);
    }  

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

    public List<Goal> getAllTasks() {
        return goalRepository.findAll();
    }


    public Goal addGoalByUser(User user, AddGoalDTO addGoalDTO) {

        Goal goal = new Goal();
        goal.setObjective(addGoalDTO.getObjective());
        goal.setDescription(addGoalDTO.getDescription());
        goal.setType(addGoalDTO.getType());
        goal.setStartDate(addGoalDTO.getEndDay());
        goal.setEndDate(addGoalDTO.getEndDay());
        goal.setUser(user);

        user.getGoals().add(goal);

        return goalRepository.save(goal);
    }

    public Goal updateGoalByUser(User user, UpdateGoalDTO updateGoalDTO) {

        Goal goal = getGoalById(updateGoalDTO.getGoalId());
        goal.setObjective(updateGoalDTO.getObjective());
        goal.setDescription(updateGoalDTO.getDescription());
        goal.setType(updateGoalDTO.getType());
        goal.setStatus(updateGoalDTO.getStatus());
        goal.setStartDate(updateGoalDTO.getEndDay());
        goal.setEndDate(updateGoalDTO.getEndDay());

        return goalRepository.save(goal);
    }

    public List<Goal> getGoalsByUser(User user) {
        return goalRepository.findByUser(user);
    }

    public void deleteGoalById(Long goalId) {
        goalRepository.deleteById(goalId);
    }

    public void update(Goal goal) {
        goalRepository.save(goal);
    }

    public Goal persist(Goal goal) {
        return goalRepository.save(goal);
    }

}
