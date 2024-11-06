package com.hacheery.todoapp.repository;

import com.hacheery.todoapp.entity.Task;
import com.hacheery.todoapp.enums.EPriority;
import com.hacheery.todoapp.enums.ETaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE (:status IS NULL OR t.status = :status) " +
            "AND (:assigneeId IS NULL OR t.assigneeId = :assigneeId) " +
            "AND (:priority IS NULL OR t.priority = :priority) " +
            "AND (:completed IS NULL OR t.completed = :completed)")
    Page<Task> findTasksByFilter(@Param("status") ETaskStatus status,
                                 @Param("assigneeId") Long assigneeId,
                                 @Param("completed") Boolean completed,
                                 @Param("priority") EPriority priority,
                                 Pageable pageable
    );
}
