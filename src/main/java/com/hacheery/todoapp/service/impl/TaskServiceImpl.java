package com.hacheery.todoapp.service.impl;

import com.hacheery.todoapp.entity.Task;
import com.hacheery.todoapp.enums.EPriority;
import com.hacheery.todoapp.enums.ETaskStatus;
import com.hacheery.todoapp.repository.TaskRepository;
import com.hacheery.todoapp.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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
    public Page<Task> getAllTasks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return taskRepository.findAll(pageable);
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
    public Page<Task> findTasksByFilter(
            ETaskStatus status, Long assigneeId, Boolean completed,
            EPriority priority, int page, int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return taskRepository.findTasksByFilter(status, assigneeId, completed, priority, pageable);
    }
}
