package com.example.flow.controllers;

import com.example.flow.entities.Task;
import com.example.flow.entities.enums.TaskStatus;
import com.example.flow.mapper.dtos.TaskDto;
import com.example.flow.mapper.mappers.TaskMapper;
import com.example.flow.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody @Valid TaskDto task) {
        Task save = taskService.save(TaskMapper.toEntity(task));
        return ResponseEntity.ok(save);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<Task> all = taskService.findAll();
        return ResponseEntity.ok(all.stream().map(TaskDtoMapper::mapToDto).toList());
    }

    @PostMapping("{id}/request-change")
    public ResponseEntity<Object> requestChangeTask(@PathVariable("id") Long id) {
        taskService.requestChangeTask(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("{id}/change-status/{status}")
    public ResponseEntity<Object> changeStatus(@PathVariable("id") Long id, @PathVariable("status") TaskStatus status) {
        taskService.changeStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{id}/assign/{userId}")
    public ResponseEntity<Object> assignTask(@PathVariable("id") Long id, @PathVariable("userId")  Long userId) {
        taskService.assignTask(id, userId);
        return ResponseEntity.ok().build();
    }

}
