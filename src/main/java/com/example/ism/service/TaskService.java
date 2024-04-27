package com.example.ism.service;

import com.example.ism.model.Task;

import java.util.List;

public interface TaskService {
    public Task addTask(Task task);
    public Task deleteTaskById(long id);
    public List<Task> findAllTasks();
    public Task updateTask(long id, Task task);
    //@Cacheable ("tasks")
    public Task findTaskById(long id);
}
