package com.example.flow.services.impl;

import com.example.flow.entities.Tag;
import com.example.flow.entities.Task;
import com.example.flow.entities.TaskModifier;
import com.example.flow.entities.User;
import com.example.flow.entities.enums.RequestStatus;
import com.example.flow.entities.enums.TaskStatus;
import com.example.flow.entities.enums.TokenType;
import com.example.flow.repositories.TaskModifierRepo;
import com.example.flow.repositories.TaskRepo;
import com.example.flow.services.TagService;
import com.example.flow.services.TaskService;


import com.example.flow.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final User authUser ;

    private final TaskRepo taskRepo;
    private final UserService userService;
    private final TagService tagService;
    private final TaskModifierRepo taskModifierRepo;

    @Override
    @Transactional
    public void requestChangeTask(Long id){
        Task task = findById(id);
        canTaskBeChanged(task, authUser);

        authUser.setNumberOfChangeTokens(authUser.getNumberOfChangeTokens() - 1);
        taskModifierRepo.save(TaskModifier.builder()
                .tokenType(TokenType.CHANGE_TOKEN)
                .dateRequest(LocalDateTime.now())
                .task(task)
                .status(RequestStatus.PENDING)
                .oldOwnerId(task.getUser().getId())
                .build());
    }

    public Task findById(Long id) {
        return taskRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Task with id"+ id +  " not found "));
    }

    @Override
    public void delete(Long id){
        Task task = findById(id);

        if (!task.getCreatedBy().equals(authUser)) throw new IllegalArgumentException("You can't delete this task");
        taskRepo.delete(task);
    }


    @Override
    public List<Task> findAll() {
        return taskRepo.findAll();
    }

    @Override
    public Task save(Task task) {
        canTakeBeCreated(task);
        validateTags(task);

        task.setStatus(TaskStatus.TODO);
        task.setCreatedBy(authUser);
        if (task.getUser() != null)
            task.setAssignedDate(LocalDateTime.now());
        return taskRepo.save(task);
    }

    @Override
    public void assignTask(Long taskId, Long userId){
        Task task = findById(taskId);
        User user = userService.findById(userId).orElseThrow(()-> new IllegalArgumentException("User Not found "));
        canTaskBeAssigned(task);
        task.setUser(user);
        task.setAssignedDate(LocalDateTime.now());
        taskRepo.save(task);
    }

    @Override
    public void changeStatus(Long taskId, TaskStatus status) {
        Task task = this.findById(taskId);
        if(status == TaskStatus.DONE && task.getDeadline().isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("Task deadline is passed");
        task.setStatus(status);
        taskRepo.save(task);
    }


    private void canTaskBeAssigned(Task task) {
        if (task.getDeadline() == null)
            throw new IllegalArgumentException("Task must have an expiration date");

        if (task.getUser() != null) {
            throw new IllegalArgumentException("This task is already assigned to someone");
        }
    }

    private void canTakeBeCreated(Task task) {
        if (task.getStartDate() != null) {
            if (task.getDeadline().toLocalDate().isAfter(LocalDate.now().plusDays(3)))
                throw new IllegalArgumentException("Task scheduling is restricted to 3 days in advance.");
            if (task.getStartDate().isBefore(LocalDateTime.now()))
                throw new IllegalArgumentException("The date is in the past !");
        }
    }

    private void validateTags(Task task) {
        if (task.getTags() == null || task.getTags().size() < 2) {
            throw new IllegalArgumentException("At least 2 tags is required !");
        }
        List<Tag> tags = tagService.findByNameIn(task.getTags().stream().map(Tag::getName).toList());
        task.setTags(tags);
    }

    private void canTaskBeChanged(Task task, User principal) {
        if(task.getUser() == null)
            throw new IllegalArgumentException("This task is not assigned to anyone");
        if (task.isHasChanged())
            throw new IllegalArgumentException("This task has already been changed");
        if (principal.getNumberOfChangeTokens() <= 0)
            throw new IllegalArgumentException("You don't have enough change tokens");
    }

}
