package com.example.flow.mapper.dtos;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.example.flow.entities.Tag}
 */
public record TagDto(Long id,@NotNull(message = "Name must not be null") String name) implements Serializable {
}