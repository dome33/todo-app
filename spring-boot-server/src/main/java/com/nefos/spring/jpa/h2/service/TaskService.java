package com.nefos.spring.jpa.h2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nefos.spring.jpa.h2.model.Task;
import com.nefos.spring.jpa.h2.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(String title) {
        List<Task> tasks = new ArrayList<>();

        if (title == null || title.isEmpty()) {
            taskRepository.findAll().forEach(tasks::add);
        } else {
            taskRepository.findByTitleContainingIgnoreCase(title).forEach(tasks::add);
        }

        return tasks;
    }

    public Optional<Task> getTaskById(long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {
        return taskRepository.save(new Task(task.getTitle(), task.getDescription(), false));
    }

    public Optional<Task> updateTask(long id, Task updatedTask) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setCompleted(updatedTask.isCompleted());
            return taskRepository.save(task);
        });
    }

    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }

    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }

    public List<Task> findCompletedTasks() {
        return taskRepository.findByCompleted(true);
    }
}
