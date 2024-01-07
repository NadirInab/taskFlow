package com.example.flow;

import com.example.flow.entities.Tag;
import com.example.flow.entities.Task;
import com.example.flow.entities.TaskModifier;
import com.example.flow.entities.User;
import com.example.flow.entities.enums.RequestStatus;
import com.example.flow.entities.enums.TaskStatus;
import com.example.flow.repositories.TaskModifierRepo;
import com.example.flow.repositories.TaskRepo;
import com.example.flow.services.TagService;
import com.example.flow.services.UserService;
import com.example.flow.services.impl.TaskServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TaskServiceImplTest {

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskRepo taskRepo;

    @Mock
    private UserService userService;

    @Mock
    private TagService tagService;

    @Mock
    private TaskModifierRepo taskModifierRepo;

    @Mock
    private User authUser;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(authUser.getNumberOfChangeTokens()).thenReturn(1);
    }

    @Test
    public void testRequestChangeTask() {
        Task task = new Task();
        task.setId(1L);
        when(taskRepo.findById(1L)).thenReturn(java.util.Optional.of(task));

        taskService.requestChangeTask(1L);

        assertEquals(RequestStatus.PENDING, task.getTaskChangeRequest().getStatus());
        verify(taskRepo, times(1)).findById(1L);
        verify(taskRepo, times(1)).save(task);
        verify(taskModifierRepo, times(1)).save(any(TaskModifier.class));
    }

    @Test
    public void testDeleteTask() {
        Task task = new Task();
        task.setId(1L);
        User createdByUser = new User();
        task.setCreatedBy(createdByUser);
        when(taskRepo.findById(1L)).thenReturn(java.util.Optional.of(task));
        when(authUser.getNumberOfChangeTokens()).thenReturn(1);
        taskService.delete(1L);
        verify(taskRepo, times(1)).findById(1L);
        verify(taskRepo, times(1)).delete(any(Task.class));
    }

    @Test
    public void testSaveTask() {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Sample Task");
        task.setStatus(TaskStatus.TODO);
        task.setDeadline(LocalDateTime.now().plusDays(5));
        Tag tag1 = new Tag();
        tag1.setId(1L);
        tag1.setName("Tag1");

        Tag tag2 = new Tag();
        tag2.setId(2L);
        tag2.setName("Tag2");

        task.setTags(Arrays.asList(tag1, tag2));

        when(authUser.getNumberOfChangeTokens()).thenReturn(2);
        when(tagService.findByNameIn(any())).thenReturn(Arrays.asList(tag1, tag2));
        when(taskRepo.save(task)).thenReturn(task);

        Task savedTask = taskService.save(task);

        assertEquals(TaskStatus.TODO, savedTask.getStatus());
        verify(tagService, times(1)).findByNameIn(any());
        verify(taskRepo, times(1)).save(task);
    }
}
