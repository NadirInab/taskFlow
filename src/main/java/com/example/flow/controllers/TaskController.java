package com.example.flow.controllers;

import com.example.flow.entities.Task;
import com.example.flow.entities.enums.TaskStatus;
import com.example.flow.mapper.TaskMapper;
import com.example.flow.mapper.dtos.TaskDto;
import com.example.flow.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper ;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody @Valid TaskDto taskDto) {
        Task savedTask = taskService.save(taskMapper.toEntity(taskDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<Task> allTasks = taskService.findAll();
        return ResponseEntity.ok(allTasks.stream().map(taskMapper::toDto).toList());
    }

    @PostMapping("{id}/request-change")
    public ResponseEntity<Object> requestChangeTask(@PathVariable("id") Long id) {
        taskService.requestChangeTask(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{id}/change-status/{status}")
    public ResponseEntity<Object> changeStatus(
            @PathVariable("id") Long id,
            @PathVariable("status") TaskStatus status
    ) {
        taskService.changeStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{id}/assign/{userId}")
    public ResponseEntity<Object> assignTask(@PathVariable("id") Long id, @PathVariable("userId") Long userId) {
        taskService.assignTask(id, userId);
        return ResponseEntity.ok().build();
    }
}
