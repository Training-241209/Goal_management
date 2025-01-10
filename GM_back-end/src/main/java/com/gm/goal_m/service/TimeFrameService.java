package com.gm.goal_m.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gm.goal_m.model.TimeFrame;
import com.gm.goal_m.repository.TimeFrameRepository;

@Service
public class TimeFrameService {

    private TimeFrameRepository timeFrameRepository;

    @Autowired
    public TimeFrameService(TimeFrameRepository timeFrameRepository){
        this.timeFrameRepository = timeFrameRepository;
    }
    
    public TimeFrame updateTimeFrame(TimeFrame timeframe) {
        return timeFrameRepository.save(timeframe);
    }

    public TimeFrame persist(TimeFrame timeframe) {
        return timeFrameRepository.save(timeframe);
    }
    
}
