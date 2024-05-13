package com.example.ism.controller;

import com.example.api.TaskApi;
import com.example.ism.model.Task;
import com.example.ism.model.TaskStatus;
import com.example.ism.service.CrewService;
import com.example.ism.service.EventService;
import com.example.ism.service.TaskService;
import com.example.model.TaskDTO;
import com.example.model.TaskWithSkillsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController implements TaskApi {

    @Autowired
    TaskService taskService;

    @Autowired
    EventService eventService;

    @Autowired
    CrewService crewService;

    @Override
    public ResponseEntity<TaskDTO> createTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setName(taskDTO.getName());
        task.setXLocation(taskDTO.getxLocation().doubleValue());
        task.setYLocation(taskDTO.getyLocation().doubleValue());
        task.setDeadline(taskDTO.getDeadline().toLocalDate());

        switch (taskDTO.getStatus()) {
            case IN_PROGRESS:
                task.setTaskStatus(TaskStatus.IN_PROGRESS);
                break;
            case DONE:
                task.setTaskStatus(TaskStatus.DONE);
                break;
            default:
                task.setTaskStatus(TaskStatus.TODO);
                break;
        }

        task.setEvent(eventService.detailedEvent(taskDTO.getEventId()));
        task.setAssignee(crewService.getCrewMember(taskDTO.getAssigneeId(), taskDTO.getEventId()));

        taskService.addTask(task);

        return new ResponseEntity<>(taskDTO, org.springframework.http.HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteTask(Integer taskId) {
        if(taskService.deleteTaskById(taskId) == null) {
            return new ResponseEntity<>(org.springframework.http.HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(org.springframework.http.HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<Task> tasks = taskService.findAllTasks();
        List<TaskDTO> taskDTOs = new ArrayList<>();

        tasks.forEach(
                task -> {
                    TaskDTO taskDTO = new TaskDTO();
                    taskDTO.setId(task.getId());
                    taskDTO.setName(task.getName());
                    taskDTO.setxLocation(BigDecimal.valueOf(task.getXLocation()));
                    taskDTO.setyLocation(BigDecimal.valueOf(task.getYLocation()));
                    taskDTO.setDeadline(OffsetDateTime.from(task.getDeadline()));
                    taskDTO.setEventId(task.getEvent().getId());
                    taskDTO.setAssigneeId(task.getAssignee().getId());
                    taskDTO.setStatus(TaskDTO.StatusEnum.valueOf(task.getTaskStatus().toString()));
                    taskDTOs.add(taskDTO);
                }
        );

        return new ResponseEntity<>(taskDTOs, org.springframework.http.HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TaskWithSkillsDTO>> getTaskSkills(Integer taskId) {
        return TaskApi.super.getTaskSkills(taskId);
    }

    @Override
    public ResponseEntity<TaskDTO> updateTask(Integer taskId, TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(Long.valueOf(taskId));
        task.setName(taskDTO.getName());
        task.setXLocation(taskDTO.getxLocation().doubleValue());
        task.setYLocation(taskDTO.getyLocation().doubleValue());
        task.setDeadline(taskDTO.getDeadline().toLocalDate());

        switch (taskDTO.getStatus()) {
            case IN_PROGRESS:
                task.setTaskStatus(TaskStatus.IN_PROGRESS);
                break;
            case DONE:
                task.setTaskStatus(TaskStatus.DONE);
                break;
            default:
                task.setTaskStatus(TaskStatus.TODO);
                break;
        }

        task.setEvent(eventService.detailedEvent(taskDTO.getEventId()));
        task.setAssignee(crewService.getCrewMember(taskDTO.getAssigneeId(), taskDTO.getEventId()));

        taskService.addTask(task);

        return new ResponseEntity<>(taskDTO, org.springframework.http.HttpStatus.OK);
    }
}
