package com.example.flow.services;

import com.example.flow.entities.Task;
import com.example.flow.entities.enums.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

    void requestChangeTask(Long id) ;
    Task findById(Long id) ;
    void delete(Long id) ;
    List<Task> findAll();
    Task save(Task task) ;
    void assignTask(Long taskId, Long userId) ;
    void changeStatus(Long taskId, TaskStatus status) ;
}
