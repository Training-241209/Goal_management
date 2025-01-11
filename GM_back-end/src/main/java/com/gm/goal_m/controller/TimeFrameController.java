package com.gm.goal_m.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gm.goal_m.service.TimeFrameService;

@Controller
@RequestMapping("/api")
public class TimeFrameController {
    private TimeFrameService timeFrameService;

    public TimeFrameController (TimeFrameService timeFrameService){
        this.timeFrameService = timeFrameService;
    }
    

}
