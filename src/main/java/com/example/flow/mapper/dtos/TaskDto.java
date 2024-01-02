package com.example.flow.mapper.dtos;

import com.example.flow.entities.enums.TaskStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link com.example.flow.entities.Task}
 */
public record TaskDto(Long id, String title, String description, LocalDateTime deadline, LocalDateTime startDate,
                      LocalDateTime assignedDate, TaskStatus status, List<TagDto1> tags, UserDto createdBy,
                      UserDto user, TaskModifierDto taskChangeRequest) implements Serializable {
    /**
     * DTO for {@link com.example.flow.entities.Tag}
     */
    public record TagDto1(Long id) implements Serializable {
    }

    /**
     * DTO for {@link com.example.flow.entities.User}
     */
    public record UserDto(Long id) implements Serializable {
    }


    /**
     * DTO for {@link com.example.flow.entities.TaskModifier}
     */
    public record TaskModifierDto(Long id) implements Serializable {
    }
}