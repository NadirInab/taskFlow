package com.example.flow.mapper.dtos;

import com.example.flow.entities.enums.TaskStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link com.example.flow.entities.Task}
 */
public record TaskDto(Long id, @NotNull(message = "title souldn't be empty") String title, @NotEmpty(message = "description is required!") String description, LocalDateTime deadline, LocalDateTime startDate,
                      LocalDateTime assignedDate, @NotNull(message = "Status is required !!") TaskStatus status, List<TagDto> tags) implements Serializable {
    /**
     * DTO for {@link com.example.flow.entities.Tag}
     */
    public record TagDto1(Long id, String name) implements Serializable {
    }
}