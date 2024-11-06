package com.hacheery.todoapp.entity;

import com.hacheery.todoapp.enums.EPriority;
import com.hacheery.todoapp.enums.ETaskStatus;
import com.hacheery.todoapp.model.BaseAudit;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task extends BaseAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Title is mandatory")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters\"")
    private String title;

    @Enumerated(EnumType.STRING)
    private ETaskStatus status;

    @Column(columnDefinition = "TEXT")
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @FutureOrPresent(message = "Due date cannot be in the past")
    private LocalDate dueDate;

    private boolean completed;

    private Long userId;

    private Long assigneeId;

    @NotNull(message = "Priority is required")
    @Enumerated(EnumType.STRING)
    private EPriority priority;
}