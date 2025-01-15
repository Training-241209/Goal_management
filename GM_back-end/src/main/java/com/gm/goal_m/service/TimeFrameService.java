package com.gm.goal_m.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gm.goal_m.dto.TaskDTOs.GetTaskIdDTO;
import com.gm.goal_m.dto.TimeFrameDTOs.AddTimeFrameByTaskIdDTO;
import com.gm.goal_m.dto.TimeFrameDTOs.UpdateTimeFrameDTO;
import com.gm.goal_m.exception.custom.DuplicateException;
import com.gm.goal_m.model.Task;
import com.gm.goal_m.model.TimeFrame;
import com.gm.goal_m.repository.TimeFrameRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@Service
public class TimeFrameService {
    private TimeFrameRepository timeFrameRepository;
    private TaskService taskService;

    @Autowired
    public TimeFrameService(TimeFrameRepository timeFrameRepository, TaskService taskService) {
        this.timeFrameRepository = timeFrameRepository;
        this.taskService = taskService;
    }

    public TimeFrame update(TimeFrame timeFrame) {
        return timeFrameRepository.save(timeFrame);
    }

    public TimeFrame persist(TimeFrame timeFrame) {
        return timeFrameRepository.save(timeFrame);
    }

    @Transactional
    public void addTimeFrameRangeToTask(Task task, AddTimeFrameByTaskIdDTO dto) {


        List<LocalDate> dates = new ArrayList<>();
        LocalDate currentDate = dto.getStartDate();

        while (!currentDate.isAfter(dto.getEndDate())) {
            dates.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }

        for (LocalDate Date : dates) {
            if (exists(Date, dto.getStartTime(), dto.getEndTime())) {
                throw new DuplicateException("TimeFrames can't be added because they overlap with other timeframes");
            }
        }

        for (LocalDate Date : dates) {
            TimeFrame timeFrame = new TimeFrame();
            timeFrame.setObjective(dto.getObjective());
            timeFrame.setDate(Date);
            timeFrame.setStartTime(dto.getStartTime());
            timeFrame.setEndTime(dto.getEndTime());
            timeFrame.setTask(task);

            persist(timeFrame);
        }

        // task.getTimeFrames().add(timeFrame);

        // taskService.update(task);
    }

    public boolean exists(LocalDate day, LocalTime startTime, LocalTime endTime) {

        List<TimeFrame> Tf = timeFrameRepository.findOverlappingTimeFrames(day, startTime, endTime);
        return !Tf.isEmpty();
    }

    public TimeFrame updateTimeFrame(UpdateTimeFrameDTO updateTimeFrameDTO) {
        TimeFrame timeFrame = getTimeFrameById(updateTimeFrameDTO.getTimeFrameId());
        timeFrame.setObjective(updateTimeFrameDTO.getObjective());
        timeFrame.setDate(updateTimeFrameDTO.getDate());
        timeFrame.setStartTime(updateTimeFrameDTO.getStartTime());
        timeFrame.setEndTime(updateTimeFrameDTO.getEndTime());
        timeFrame.setStatus(updateTimeFrameDTO.getStatus());

        return timeFrameRepository.save(timeFrame);
    }

    public TimeFrame getTimeFrameById(Long timeFrameId) {
        Optional<TimeFrame> timeFrameOpt = timeFrameRepository.findById(timeFrameId);
        if (!timeFrameOpt.isPresent()) {
            throw new UnsupportedOperationException("Timeframe NotFound");
        }

        return timeFrameOpt.get();
    }

    public void deleteTimeFrameById(Long id) {
        timeFrameRepository.deleteById(id);
    }
}
