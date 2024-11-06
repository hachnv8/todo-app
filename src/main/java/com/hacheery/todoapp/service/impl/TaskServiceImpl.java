package com.hacheery.todoapp.service.impl;

import com.hacheery.todoapp.entity.Task;
import com.hacheery.todoapp.enums.ETaskStatus;
import com.hacheery.todoapp.repository.TaskRepository;
import com.hacheery.todoapp.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated // Annotation để kích hoạt validation trong service layer
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Task with ID " + taskId + " not found")
                );
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task updateTask(Long taskId, Task taskDetails) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task with ID " + taskId + " not found"));

        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setPriority(taskDetails.getPriority());

        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task with ID " + taskId + " not found"));

        taskRepository.delete(task);
    }

    @Override
    public List<Task> findTasksByStatus(ETaskStatus status) {
        return List.of();
    }

    @Override
    public List<Task> findTasksByAssignee(Long assigneeId) {
        return List.of();
    }

    @Override
    public void completeTask(Long taskId) {

    }

    @Override
    public void reopenTask(Long taskId) {

    }

    @Override
    public void assignTaskToUser(Long taskId, Long userId) {

    }
}
