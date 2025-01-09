package com.gm.goal_m.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gm.goal_m.model.Goal;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long>{
    List<Goal> findByUserId(Long userId);
}
