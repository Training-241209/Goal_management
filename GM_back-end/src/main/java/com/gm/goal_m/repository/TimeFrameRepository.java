package com.gm.goal_m.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gm.goal_m.model.Task;
import com.gm.goal_m.model.TimeFrame;

@Repository
public interface TimeFrameRepository extends JpaRepository <TimeFrame, Long>{

    List <TimeFrame> findByTask(Task task);

    
    
}
