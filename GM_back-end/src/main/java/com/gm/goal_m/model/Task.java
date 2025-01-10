package com.gm.goal_m.model;

import java.util.ArrayList;
import java.util.List;

import com.gm.goal_m.Util.Enums.TaskStatus;
import com.gm.goal_m.Util.Enums.TaskType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private TaskType type;
    
    private TaskStatus status = TaskStatus.PENDING;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List <TimeFrame> timeFrames = new ArrayList<> ();
      
    
}


