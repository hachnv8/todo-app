package com.hacheery.todoapp.service;

import com.hacheery.todoapp.entity.Task;
import com.hacheery.todoapp.enums.EPriority;
import com.hacheery.todoapp.enums.ETaskStatus;
import org.springframework.data.domain.Page;

public interface TaskService {

    Task createTask(Task task);

    Task getTaskById(Long taskId);

    Page<Task> getAllTasks(int page, int size);

    Task updateTask(Long taskId, Task taskDetails);

    void deleteTask(Long taskId);

    Page<Task> findTasksByFilter(
            ETaskStatus status, Long assigneeId, Boolean completed,
            EPriority priority, int page, int size
    );
}