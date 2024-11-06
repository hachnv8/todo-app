package com.hacheery.todoapp.controller;

import com.hacheery.todoapp.entity.Task;
import com.hacheery.todoapp.enums.EPriority;
import com.hacheery.todoapp.enums.ETaskStatus;
import com.hacheery.todoapp.payload.response.ApiResponse;
import com.hacheery.todoapp.service.impl.TaskServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskServiceImpl taskService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Page<Task>>> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Task> tasks = taskService.getAllTasks(page, size);
        ApiResponse<Page<Task>> response = new ApiResponse<>(
                true,
                "Tasks retrieved successfully",
                tasks,
                HttpStatus.OK.value()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Task>> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        ApiResponse<Task> response = new ApiResponse<>(
                true,
                "Task retrieved successfully",
                task,
                HttpStatus.OK.value()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Task>> createTask(@RequestBody @Valid Task task) {
        Task createdTask = taskService.createTask(task);
        ApiResponse<Task> response = new ApiResponse<>(
                true,
                "Task created successfully",
                createdTask,
                HttpStatus.CREATED.value()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Task>> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Task updatedTask = taskService.updateTask(id, taskDetails);
        ApiResponse<Task> response = new ApiResponse<>(
                true,
                "Task updated successfully",
                updatedTask,
                HttpStatus.OK.value()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Task with ID " + id + " has been deleted successfully.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<Task>>> findTasksByFilter(
            @RequestParam(required = false) ETaskStatus status,
            @RequestParam(required = false) Long assigneeId,
            @RequestParam(required = false) Boolean completed,
            @RequestParam(required = false) EPriority priority,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Task> tasks = taskService.findTasksByFilter(
                status, assigneeId, completed,
                priority, page, size);

        ApiResponse<Page<Task>> response = new ApiResponse<>(
                true,
                "Tasks retrieved successfully",
                tasks,
                HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
