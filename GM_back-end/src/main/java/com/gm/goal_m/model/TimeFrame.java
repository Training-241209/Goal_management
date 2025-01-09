package com.gm.goal_m.model;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "timeFrame")
public class TimeFrame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean status;


    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
}
