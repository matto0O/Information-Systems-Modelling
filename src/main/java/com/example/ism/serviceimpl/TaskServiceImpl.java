package com.example.ism.serviceimpl;

import com.example.ism.aspects.Log;
import com.example.ism.model.Task;
import com.example.ism.repository.TaskRepository;
import com.example.ism.service.TaskService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Log
    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Log
    @Override
    public Task deleteTaskById(long id) {
        Optional<Task> e = taskRepository.findById(id);
        if (e.isPresent()) {
            Task existingTask = e.get();
            taskRepository.deleteById(id);
            return existingTask;
        }
        return null;
    }

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    @Log
    @Override
    public Task updateTask(long id, Task task) {
        Optional<Task> e = taskRepository.findById(id);
        if (e.isPresent()) {
            Task existingTask = e.get();
            existingTask.setName(task.getName());
            existingTask.setDescription(task.getDescription());
            existingTask.setDeadline(task.getDeadline());
            existingTask.setTaskStatus(task.getTaskStatus());
            existingTask.setAssignee(task.getAssignee());
            existingTask.setEvent(task.getEvent());
            existingTask.setXLocation(task.getXLocation());
            existingTask.setYLocation(task.getYLocation());
            return taskRepository.save(existingTask);
        }
        task.setId(id);
        return taskRepository.save(task);
    }

    @Log
    @Override
    public Task findTaskById(long id) {
        return taskRepository.findById(id).orElse(null);
    }
}
