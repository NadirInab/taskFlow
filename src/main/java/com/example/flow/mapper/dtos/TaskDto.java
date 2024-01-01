package com.example.flow.mapper.dtos;

import com.example.flow.entities.enums.TaskStatus;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.flow.entities.Task}
 */
public record TaskDto(Long id, String title, String description, LocalDateTime deadline, LocalDateTime startDate,
                      @NotNull TaskStatus status) implements Serializable {
}