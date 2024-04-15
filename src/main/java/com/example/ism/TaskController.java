package com.example.ism;

import com.example.api.TaskApi;
import com.example.model.Task;
import com.example.model.TaskWithSkills;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController implements TaskApi {
    @Override
    public ResponseEntity<Task> createTask(Task task) {
        return TaskApi.super.createTask(task);
    }

    @Override
    public ResponseEntity<Void> deleteTask(Integer taskId) {
        return TaskApi.super.deleteTask(taskId);
    }

    @Override
    public ResponseEntity<List<Task>> getAllTasks() {
        return TaskApi.super.getAllTasks();
    }

    @Override
    public ResponseEntity<List<TaskWithSkills>> getTaskSkills(Integer taskId) {
        return TaskApi.super.getTaskSkills(taskId);
    }

    @Override
    public ResponseEntity<Task> updateTask(Integer taskId, Task task) {
        return TaskApi.super.updateTask(taskId, task);
    }
}
