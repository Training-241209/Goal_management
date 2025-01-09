package com.gm.goal_m.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "goal")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean status = false;

    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; 
}
