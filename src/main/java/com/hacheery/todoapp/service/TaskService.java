package com.hacheery.todoapp.service;

import com.hacheery.todoapp.entity.Task;
import com.hacheery.todoapp.enums.ETaskStatus;

import java.util.List;

public interface TaskService {

    Task createTask(Task task);

    Task getTaskById(Long taskId);

    List<Task> getAllTasks();

    Task updateTask(Long taskId, Task taskDetails);

    void deleteTask(Long taskId);

    List<Task> findTasksByStatus(ETaskStatus status);

    List<Task> findTasksByAssignee(Long assigneeId);

    void completeTask(Long taskId);

    void reopenTask(Long taskId);

    void assignTaskToUser(Long taskId, Long userId);
}