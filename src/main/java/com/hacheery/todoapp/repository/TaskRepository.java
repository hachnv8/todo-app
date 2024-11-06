package com.hacheery.todoapp.repository;

import com.hacheery.todoapp.entity.Task;
import com.hacheery.todoapp.enums.ETaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTasksByStatus(ETaskStatus status);
    List<Task> findTasksByAssigneeId(Long assigneeId);
}
